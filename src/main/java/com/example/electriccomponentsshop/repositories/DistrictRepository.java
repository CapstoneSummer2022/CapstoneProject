package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.District;
import com.example.electriccomponentsshop.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    Optional<District> findByName(String name);

    @Override
    List<District> findAll();
    List<District> findByProvinceName(String provinceName);
}
