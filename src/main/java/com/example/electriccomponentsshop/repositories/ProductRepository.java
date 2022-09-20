package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Override
    List<Product> findAll();
    @Query(value = "select  * from product where name like %:pName% COLLATE utf8mb4_0900_ai_ci",nativeQuery = true)
    Page<Product> searchProductsByNameContain(@Param("pName") String pName,Pageable pageable);
    @Override
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Integer id);
    List<Product> findDistinctByName(String name);
    Page<Product> findProductsByProductSupplierId(Integer sId,Pageable pageable);
    @Query(value = "select  * from product p where p.supplier_id = :sId and p.name like %:pName% COLLATE utf8mb4_0900_ai_ci",nativeQuery = true)
    List<Product> findProductsByProductSupplierIdAndNameContains(@Param("sId") Integer sId,@Param("pName") String pName);
    @Override
    <S extends Product> S save(S entity);
}
