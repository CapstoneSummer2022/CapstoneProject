package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.common.OrderEnum;
import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.dto.OrderItemDTO;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.*;
import com.example.electriccomponentsshop.services.AccountDetailImpl;
import com.example.electriccomponentsshop.services.AccountService;
import com.example.electriccomponentsshop.services.OrderKindService;
import com.example.electriccomponentsshop.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
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

    final OrderKindService orderKindService;
    @Override
    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findOrdersByStatus(status);
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
        Double totalPayment = 0.0;
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
            Integer quantity = Integer.parseInt(o.getQuantity());
            Product p = getProduct(o.getProductId());
            p.setAvailable(p.getAvailable()-quantity);
            productRepository.save(p);
            double unitPrice = p.getExportPrice().getRetailPrice();
            OrderItem orderItem = new OrderItem(new OrderItemId(order.getId(), p.getId()), unitPrice, quantity, unitPrice * quantity, order, p);
            orderItemRepository.save(orderItem);
            list.add(orderItem);
            totalPayment += quantity * p.getExportPrice().getRetailPrice();
        }

        order.setTotalPayment(totalPayment);

        order.setOrderItems(list);


        orderRepository.save(order);
        return Boolean.TRUE;
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
        double totalPayment = 0.0;
        setAddress(orderDTO, order);
        order.setDetailLocation(orderDTO.getDetailLocation());
        order.setReceivedPerson(orderDTO.getReceivedPerson());
        order.setReceivedPhone(orderDTO.getReceivedPhone());
        orderItemRepository.deleteOrderItemsByOrderId(order.getId());
        for (OrderItemDTO o : dtos
        ) {
            Integer quantity = Integer.parseInt(o.getQuantity());
            Product p = getProduct(o.getProductId());
            p.setAvailable(p.getAvailable()-quantity);
            double unitPrice = p.getExportPrice().getRetailPrice();
            OrderItem orderItem = new OrderItem(new OrderItemId(order.getId(), p.getId()), unitPrice, quantity, unitPrice * quantity, order, p);
            orderItemRepository.save(orderItem);
            totalPayment += quantity * p.getExportPrice().getRetailPrice();
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
