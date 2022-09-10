package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.ExportPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExportPriceRepository extends JpaRepository<ExportPrice,Integer> {
    Optional<ExportPrice> findByProductId(Integer productId);
}
