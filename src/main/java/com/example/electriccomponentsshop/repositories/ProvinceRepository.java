package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Province;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, String> {
    @Override
    List<Province> findAll();
    Optional<Province> findByName(String name);
}