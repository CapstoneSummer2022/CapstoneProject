<<<<<<< HEAD:src/main/java/shop/services/order/OrderItemServiceImpl.java
package shop.services.order;

import shop.config.ModelMap;
import shop.db.dto.order.OrderItemDTO;
import shop.db.entities.OrderItem;
import shop.db.repositories.OrderItemRepository;
=======
package shop.services.impl;

import shop.config.ModelMap;
import shop.db.dto.OrderItemDto;
import shop.db.entities.OrderItem;
import shop.db.repositories.OrderItemRepository;
import shop.services.OrderItemService;
>>>>>>> b767a4d (Refactor Code):src/main/java/shop/services/impl/OrderItemServiceImpl.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ModelMap modelMap;

    @Override
    public OrderItemDTO convertToDto(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = modelMap.modelMapper().map(orderItem, OrderItemDTO.class);
        orderItemDTO.setProductName(orderItem.getProduct().getName());
        return orderItemDTO;
    }

    @Override
    public List<OrderItemDTO> findAll() {
        return orderItemRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<OrderItemDTO> findByOrderId(Integer id) {
        return orderItemRepository.findByOrderId(id).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrderItemsByOrderId(Integer orderId) {
        orderItemRepository.deleteOrderItemsByOrderId(orderId);
    }

    @Override
    public <S extends OrderItem> S save(S entity) {
        return orderItemRepository.save(entity);
    }
}
