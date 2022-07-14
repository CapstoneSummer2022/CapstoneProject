package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
