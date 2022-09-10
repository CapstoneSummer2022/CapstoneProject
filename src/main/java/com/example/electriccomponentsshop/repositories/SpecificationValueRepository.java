package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.SpecificationValue;
import com.example.electriccomponentsshop.entities.SpecificationValueId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationValueRepository extends JpaRepository<SpecificationValue, SpecificationValueId> {
    @Override
    List<SpecificationValue> findAll();
    void deleteSpecificationValuesByProductId(Integer pId);

}
