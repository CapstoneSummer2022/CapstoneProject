package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
