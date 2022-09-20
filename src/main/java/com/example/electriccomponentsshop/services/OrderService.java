package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.entities.Order;
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

    List<OrderDTO> findAllOrderForCustomer (int accId);

    List<OrderDTO> findOrderByStatusForCustomer (int accId, String status);

    OrderDTO convertToDTO(Order order);

    Order getById(String id);

    <S extends Order> S save(S entity);

    boolean createOrder(OrderDTO orderDTO);

    List<OrderDTO> findByStatus(String status);

    OrderDTO getOrderDtoById(String id);


    Order getById(String id);

    <S extends Order> S save(S entity);

    boolean createOrder(OrderDTO orderDTO);

    int createOrderOnline(Map<String, String> order);

    List<OrderDTO> findByStatus(String status);

    OrderDTO getOrderDtoById(String id);

    boolean updateOrder(String id, OrderDTO orderDTO);

    boolean updateStatus(Integer id);

    OrderDTO findById(Integer integer);

    void cancelOrder(String id);

    void returnedOrder(String id);
}
