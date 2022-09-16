package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.ListProductResponse;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO convertToDto(Product product);

    ProductDTO getProductDtoById(String id);

    Product getById(String id);

    boolean addProduct(ProductDTO productDTO);

    List<ProductDTO> findAll();

    <S extends Product> S save(S entity);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Integer integer);

    ListProductResponse getProductByCategory(Pageable pageable);
}
