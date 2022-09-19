package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.entities.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {
    List<OrderDTO> findAll();

    List<Order> findOrdersByStatus(String status);

    OrderDTO convertToDTO(Order order);

    Order getById(String id);

    <S extends Order> S save(S entity);

    boolean createOrder(OrderDTO orderDTO);

    boolean createOrderOnline(Map<String, String> order);

    List<OrderDTO> findByStatus(String status);

    OrderDTO getOrderDtoById(String id);

    boolean updateOrder(String id, OrderDTO orderDTO);

    boolean updateStatus(Integer id);

    OrderDTO findById(Integer integer);
}
