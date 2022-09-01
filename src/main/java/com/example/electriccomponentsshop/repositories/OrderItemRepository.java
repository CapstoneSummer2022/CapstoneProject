package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    List<OrderItem> findByOrderId(Integer id);
}