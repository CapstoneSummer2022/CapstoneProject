package shop.services.order;

import shop.db.dto.order.OrderDTO;
import shop.db.entities.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface OrderService {
    List<OrderDTO> findAll();

    List<Order> findOrdersByStatus(String status);

    OrderDTO convertToDTO(Order order);

    boolean createOrder(OrderDTO orderDTO);

    boolean updateOrder(String id, OrderDTO orderDTO);

    boolean updateStatus(Integer id);

    OrderDTO findById(Integer integer);
}
