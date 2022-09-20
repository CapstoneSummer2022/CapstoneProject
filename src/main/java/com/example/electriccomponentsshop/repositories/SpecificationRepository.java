package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification,Integer> {
    List<Specification> findSpecificationsByIdNotIn(Integer... sId);
    Optional<Specification> findSpecificationsByName(String name);
}
