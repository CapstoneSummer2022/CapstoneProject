package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderItemDto;
import com.example.electriccomponentsshop.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> findAll();
    List<OrderItemDto> findByOrderId(Integer id);
}
