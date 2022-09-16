package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderItemDTO;
import com.example.electriccomponentsshop.entities.OrderItem;


import java.util.List;

public interface OrderItemService {
    OrderItemDTO convertToDto(OrderItem orderItem);

    List<OrderItemDTO> findAll();

    void deleteOrderItemsByOrderId(Integer orderId);

    <S extends OrderItem> S save(S entity);
    List<OrderItemDTO> findByOrderId(Integer id);
}
