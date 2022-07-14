package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Override
    List<Product> findAll();

    @Override
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Integer id);
    List<Product> findDistinctByName(String name);

    @Override
    <S extends Product> S save(S entity);
}
