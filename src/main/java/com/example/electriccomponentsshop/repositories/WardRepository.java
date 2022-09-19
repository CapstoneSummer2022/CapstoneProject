package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, String> {
    @Override
    List<Ward> findAll();
    Optional<Ward> findByName(String name);
    List<Ward> findByDistrictName(String districtName);

    @Query(value = "select * from ward where d_code = :dCode", nativeQuery = true)
    List<Ward> findByDistrictDCode (String dCode);
}
