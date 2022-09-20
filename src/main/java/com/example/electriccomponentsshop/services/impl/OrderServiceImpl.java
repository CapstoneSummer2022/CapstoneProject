package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.common.OrderEnum;
import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CartItemDTO;
import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.dto.OrderItemDTO;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.*;
import com.example.electriccomponentsshop.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;
    final AccountRepository accountRepository;
    final ModelMap modelMap;
    final ProductRepository productRepository;
    final ProvinceRepository provinceRepository;
    final DistrictRepository districtRepository;
    final WardRepository wardRepository;
    final OrderItemRepository orderItemRepository;
    final AccountService accountService;

    final CartItemService cartItemService;

    final OrderKindRepository orderKindRepo;

    final OrderKindService orderKindService;
    @Override
    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findOrdersByStatus(status);
    }

    @Override
    public List<OrderDTO> findAllOrderForCustomer(int accId) {

        return null;
    }

    @Override
    public List<OrderDTO> findOrderByStatusForCustomer(int accId, String status) {
        return null;
    }

    public OrderDTO convertToDTO(Order order) {
        return modelMap.modelMapper().map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order o : orderList
        ) {
            orderDTOList.add(convertToDTO(o));
        }
        return orderDTOList;
    }
    @Override
    public Order getById(String id){
        try{
            int oId = Integer.parseInt(id);
            Optional<Order> orderOptional = orderRepository.findById(oId);
            if(orderOptional.isPresent()){
                return orderOptional.get();
            }else throw new NoSuchElementException("Không tìm thấy đơn này");
        }catch (NumberFormatException e){
            throw new NoSuchElementException("Không tìm thấy đơn này");
        }
    }

    private boolean setAddress(OrderDTO orderDTO, Order order) {
        Optional<Province> provinceOptional = provinceRepository.findByName(orderDTO.getProvinceName());

        if (provinceOptional.isEmpty()) {
            throw new NoSuchElementException("Không tìm thấy tỉnh/thành phố này");
        } else {
            Province province = provinceOptional.get();
            List<District> districtList = province.getDistrictList();
            Optional<District> districtOptional = districtRepository.findByName(orderDTO.getDistrictName());
            if (districtOptional.isEmpty()) {
                throw new NoSuchElementException("Không tìm thấy Quận/Huyện này");
            } else {
                District district = districtOptional.get();
                if (!districtList.contains(district)) {
                    throw new NoSuchElementException("Tỉnh/Thành phố này không có Quận/Huyện này");
                } else {
                    System.out.println(orderDTO.getProvinceName() + "tên xa");
                    Optional<Ward> wardOptional = wardRepository.findByName(orderDTO.getWardName());
                    List<Ward> wardList = district.getWardList();
                    if (wardOptional.isEmpty()) {
                        throw new NoSuchElementException("Không có Xã/Phường/Thị trấn này");
                    } else {
                        Ward ward = wardOptional.get();
                        if (!wardList.contains(ward)) {
                            throw new NoSuchElementException("Quận/Huyện này không có Xã/Phường/Thị trấn này");
                        } else {
                            order.setProvinceOrder(province);
                            order.setDistrictOrder(district);
                            order.setWardOrder(ward);
                        }

                    }
                }

            }
        }
        return true;
    }

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }

    @Override
    public boolean createOrder(OrderDTO orderDTO){
        List<OrderItemDTO> orderItems = orderDTO.getOrderItems();
        BigDecimal totalPayment = BigDecimal.valueOf(0);
        Order order = new Order();
        OrderKind orderKind = orderKindService.getById(orderDTO.getKindId());
        order.setOrderKind(orderKind);
        setAddress(orderDTO, order);
        order.setDetailLocation(orderDTO.getDetailLocation());
        order.setReceivedPerson(orderDTO.getReceivedPerson());
        order.setReceivedPhone(orderDTO.getReceivedPhone());
        order.setStatus(OrderEnum.PENDING.getName());
        order =  orderRepository.save(order);

        AccountDetailImpl accountDetail = (AccountDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(accountDetail.getAuthorities().stream().map(item->item.getAuthority()).collect(Collectors.toList()).contains(ERole.ROLE_MANAGER.name())){
            Account customerAccount = accountService.getAccountCustomerByPhone(orderDTO.getAccountCustomerPhone());
            Optional<Account> accountOptional = accountRepository.findByEmail(accountDetail.getEmail());
            if (accountOptional.isEmpty()) {
                throw new NoSuchElementException("Không tìm thấy nhân viên này");
            }
            order.setAccountEmployee(accountOptional.get());
            order.setAccountCustomer(customerAccount);
        }

        List<OrderItem> list = new ArrayList<>();
        for (OrderItemDTO o : orderItems) {
            BigDecimal quantity = o.getQuantity();
            Product p = getProduct(o.getProductId());
            p.setAvailable(p.getAvailable().subtract(quantity));
            productRepository.save(p);
            BigDecimal unitPrice = p.getExportPrice().getRetailPrice();
            OrderItem orderItem = new OrderItem(new OrderItemId(order.getId(), p.getId()), unitPrice, quantity, quantity.multiply(unitPrice), order, p);
            orderItemRepository.save(orderItem);
            list.add(orderItem);
            totalPayment = quantity.multiply(p.getExportPrice().getRetailPrice());
        }

        order.setTotalPayment(totalPayment);

        order.setOrderItems(list);


        orderRepository.save(order);
        return Boolean.TRUE;
    }

    @Override
    public int createOrderOnline(Map<String, String> orderInfo) {
        Order order = new Order();
        order.setStatus(OrderEnum.PENDING.getName());
        order.setPaidMoney(BigDecimal.valueOf(0));
        order.setOrderKind(orderKindRepo.findByName("Đặt đơn online"));
        order.setReceivedPerson(orderInfo.get("name"));
        order.setReceivedPhone(orderInfo.get("phone"));

        Province province = provinceRepository.findById(orderInfo.get("province")).get();
        District district = districtRepository.findById(orderInfo.get("district")).get();
        Ward ward = wardRepository.findById(orderInfo.get("ward")).get();
        order.setProvinceOrder(province);
        order.setDistrictOrder(district);
        order.setWardOrder(ward);
        order.setDetailLocation(orderInfo.get("detailLocation"));

        AccountDetailImpl accountDetail = (AccountDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account customer = accountRepository.findById(accountDetail.getId()).get();
        Account employee = accountService.findEmployeeForOrder();
        order.setAccountCustomer(customer);
        order.setAccountEmployee(employee);

        order = orderRepository.save(order);

        List<CartItemDTO> cartItemDTOS = cartItemService.getCartItems(accountDetail.getId());
        BigDecimal total = new BigDecimal(0);
        for (CartItemDTO item : cartItemDTOS ) {
            Product product = productRepository.findById(Integer.parseInt(item.getProductDTO().getId())).get();

            OrderItem orderItem = new OrderItem(new OrderItemId(order.getId(), product.getId()), product.getExportPrice().getRetailPrice(), item.getQuantity(), item.getSubTotal(), order, product);
            orderItemRepository.save(orderItem);

            total = total.add(item.getSubTotal());

            BigDecimal available = product.getAvailable();
            product.setAvailable(available.subtract(item.getQuantity().multiply(BigDecimal.valueOf(product.getUnit()))));
            productRepository.save(product);

            cartItemService.removeCartItem(accountDetail.getId(), product.getId());
        }

        order.setTotalPayment(total);

        orderRepository.save(order);

        return  order.getId();
    }

    @Override
    public List<OrderDTO> findByStatus(String status){
        List<Order> orderList = orderRepository.findOrdersByStatus(status);
        if(orderList.isEmpty()){
            throw new NoSuchElementException("Không có đơn hàng nào có trạng thái "+status);
        }else  return orderList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Order getOrderById(String id) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(Integer.parseInt(id));
            if (orderOptional.isEmpty()) {
                throw new NoSuchElementException("Không tìm thấy đơn hàng này");
            } else return orderOptional.get();
        } catch (NumberFormatException e) {
            throw new NoSuchElementException("Không tìm thấy đơn hàng này");
        }
    }
    @Override
    public OrderDTO getOrderDtoById(String id){
        return convertToDTO(getOrderById(id));
    }
    @Transactional
    @Override
    public boolean updateOrder(String id, OrderDTO orderDTO) {
        System.out.println("vào đây");
        System.out.println(orderDTO.getProvinceName());
        System.out.println(orderDTO.getDistrictName());
        Order order = getOrderById(id);
        List<OrderItemDTO> dtos = orderDTO.getOrderItems();
        BigDecimal totalPayment = new BigDecimal(0);
        setAddress(orderDTO, order);
        order.setDetailLocation(orderDTO.getDetailLocation());
        order.setReceivedPerson(orderDTO.getReceivedPerson());
        order.setReceivedPhone(orderDTO.getReceivedPhone());
        orderItemRepository.deleteOrderItemsByOrderId(order.getId());
        for (OrderItemDTO o : dtos
        ) {
            BigDecimal quantity = o.getQuantity();
            Product p = getProduct(o.getProductId());
            p.setAvailable(p.getAvailable().subtract(quantity));
            BigDecimal unitPrice = p. getExportPrice().getRetailPrice();
            OrderItem orderItem = new OrderItem(new OrderItemId(order.getId(), p.getId()), unitPrice, quantity, unitPrice.multiply(quantity), order, p);
            orderItemRepository.save(orderItem);
            totalPayment = quantity.multiply(p.getExportPrice().getRetailPrice());
        }
        order.setTotalPayment(totalPayment);
        setAddress(orderDTO, order);
        return orderRepository.save(order) != null;

    }

    public Product getProduct(String id) {
        try {
            Optional<Product> productOptional = productRepository.findById(Integer.parseInt(id));
            if (productOptional.isEmpty()) {
                throw new NoSuchElementException("Không tìm thấy sản phẩm có id như vậy");
            } else return productOptional.get();
        } catch (NumberFormatException e) {
            throw new NoSuchElementException("Không tìm thấy sản phẩm có id như vậy");
        }

    }

    public boolean updateStatus(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            String status = order.getStatus();
            if (status.equalsIgnoreCase(OrderEnum.PENDING.getName())) {
                order.setStatus(OrderEnum.CONFIRM.getName());
            } else if (status.equals(OrderEnum.CONFIRM.getName())) {
                order.setStatus(OrderEnum.DELIVERY.getName());
            } else if (status.equals(OrderEnum.DELIVERY.getName())) {
                order.setStatus(OrderEnum.DONE.getName());
            } else {
                throw new RuntimeException("Thao tác không được thực hiện");
            }
            orderRepository.save(order);
            return true;
        } else {
            throw new NoSuchElementException("");
        }
    }

    @Override
    public OrderDTO findById(Integer integer) {
        Optional<Order> orderOptional = orderRepository.findById(integer);
        if (orderOptional.isEmpty()) {
            throw new NoSuchElementException("Không tìm thấy đơn hàng với id này");
        }
        return convertToDTO(orderOptional.get());
    }
}
