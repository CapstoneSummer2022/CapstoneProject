package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Override
    Optional<Order> findById(Integer integer);

    @Override
    List<Order> findAll();

    List<Order> findOrdersByStatus(String status);

    @Query(value = "select * from orders where customer_id = :customerId", nativeQuery = true)
    List<Order> findByCustomerId (int customerId);

    @Query(value = "select * from orders where customer_id = :customerId and status = :status", nativeQuery = true)
    List<Order> findByCustomerIdAndStatus (int customerId, String status);

    @Override
    <S extends Order> S save(S entity);
}
