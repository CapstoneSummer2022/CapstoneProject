package shop.services;

import shop.db.dto.OrderItemDto;
import shop.db.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItemDto convertToDto(OrderItem orderItem);

    List<OrderItemDto> findAll();

    void deleteOrderItemsByOrderId(Integer orderId);

    <S extends OrderItem> S save(S entity);
    List<OrderItemDto> findByOrderId(Integer id);
}
