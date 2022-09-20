package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkuRepository extends JpaRepository<Sku,Integer> {
    Optional<Sku> findById(String code);
    List<Sku> findSkusByProductId(Integer id);
}
