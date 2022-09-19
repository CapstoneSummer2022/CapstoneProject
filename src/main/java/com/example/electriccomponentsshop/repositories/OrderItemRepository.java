package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.OrderItem;
import com.example.electriccomponentsshop.entities.OrderItemId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

    List<OrderItem> findByOrderId(Integer id);
    void deleteOrderItemsByOrderId(Integer orderId);
}
