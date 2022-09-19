package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.OrderKind;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderKindRepository extends JpaRepository<OrderKind,Integer> {

    OrderKind findByName(String name);

}
