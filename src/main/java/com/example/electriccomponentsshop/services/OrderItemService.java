package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderItemDto;
import com.example.electriccomponentsshop.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItemDto convertToDto(OrderItem orderItem);

    List<OrderItemDto> findAll();

    void deleteOrderItemsByOrderId(Integer orderId);

    <S extends OrderItem> S save(S entity);
    List<OrderItemDto> findByOrderId(Integer id);
}
