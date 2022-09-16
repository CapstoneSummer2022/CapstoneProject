package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, String> {
    @Override
    List<Ward> findAll();
    Optional<Ward> findByName(String name);
    List<Ward> findByDistrictName(String districtName);
}
