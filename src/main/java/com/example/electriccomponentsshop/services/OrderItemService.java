package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderItemDto;
import com.example.electriccomponentsshop.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAll();
    <S extends OrderItem> S save(S entity);
    List<OrderItemDto> findByOrderId(Integer id);
}
