package com.example.electriccomponentsshop.repositories;


import com.example.electriccomponentsshop.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Override
    List<Supplier> findAll();

    @Override
    <S extends Supplier> S save(S entity);

    Optional<Supplier> findSupplierByName(String name);

    Optional<Supplier> findSupplierByPhone(String phone);

    List<Supplier> findByActive(int active);
}
