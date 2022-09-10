package shop.services.order;

import shop.config.ModelMap;
import shop.db.dto.order.OrderDTO;
import shop.db.dto.order.OrderItemDTO;
import shop.db.entities.*;
import shop.db.enums.OrderEnum;
import shop.db.repositories.*;
import shop.services.AccountDetailImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

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
    public boolean createOrder(OrderDTO orderDTO) {
        List<OrderItemDTO> orderItems = orderDTO.getOrderItems();
        Double totalPayment = 0.0;
        AccountDetailImpl accountDetail = (AccountDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Account> accountOptional = accountRepository.findByEmail(accountDetail.getEmail());
        if (accountOptional.isEmpty()) {
            throw new NoSuchElementException("Không tìm thấy nhân viên này");
        }
        Order order = new Order();
        for (OrderItemDTO o : orderItems) {
            Integer quantity = Integer.parseInt(o.getQuantity());
            Product p = getProduct(o.getProductId());
            double unitPrice = p.getExportPrice().getRetailPrice();
            OrderItem orderItem = new OrderItem(new OrderItemId(order.getId(), p.getId()), unitPrice, quantity, unitPrice * quantity, order, p);
            orderItemRepository.save(orderItem);
            totalPayment += quantity * p.getExportPrice().getRetailPrice();
        }
        setAddress(orderDTO, order);
        order.setDetailLocation(orderDTO.getDetailLocation());
        order.setReceivedPerson(orderDTO.getReceivedPerson());
        order.setReceivedPhone(orderDTO.getReceivedPhone());
        order.setStatus(OrderEnum.PENDING.getName());
        order.setTotalPayment(totalPayment);
        order.setAccountEmployee(accountOptional.get());
        orderRepository.save(order);
        return Boolean.TRUE;
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
            if (status.equalsIgnoreCase("Chờ xử lý")) {
                order.setStatus("Đã xác nhận");
            } else if (status.equals("Đã xác nhận")) {
                order.setStatus("Đang giao hàng");
            } else if (status.equals("Đang giao hàng")) {
                order.setStatus("Hoàn thành");
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
