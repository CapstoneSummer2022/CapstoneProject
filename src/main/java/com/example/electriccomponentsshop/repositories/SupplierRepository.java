package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Supplier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Override
    List<Supplier> findAll();

    @Override
    <S extends Supplier> S save(S entity);
}
