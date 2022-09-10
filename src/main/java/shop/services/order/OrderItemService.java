package shop.services.order;

import shop.db.dto.order.OrderItemDTO;
import shop.db.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO convertToDto(OrderItem orderItem);

    List<OrderItemDTO> findAll();

    void deleteOrderItemsByOrderId(Integer orderId);

    <S extends OrderItem> S save(S entity);
    List<OrderItemDTO> findByOrderId(Integer id);
}
