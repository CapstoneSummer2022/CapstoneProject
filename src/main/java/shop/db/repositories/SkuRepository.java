package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuRepository extends JpaRepository<Sku,Integer> {

}
