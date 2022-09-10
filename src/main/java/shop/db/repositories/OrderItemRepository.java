package shop.db.repositories;

import shop.db.entities.OrderItem;
import shop.db.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    List<OrderItem> findByOrderId(Integer id);
    void deleteOrderItemsByOrderId(Integer orderId);
}
