package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Override
    Optional<Order> findById(Integer integer);

    @Override
    List<Order> findAll();

    List<Order> findOrdersByStatus(String status);

    @Override
    <S extends Order> S save(S entity);
}
