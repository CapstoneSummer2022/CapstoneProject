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

    void disableProduct(String id);

    void enableProduct(String id);

    Page<ProductDTO> searchProduct(String text, Pageable pageable);

    Page<ProductDTO> findBySupplierId(Pageable pageable, String sId);

    List<ProductDTO> findBySupplierIdAndNameContain(String sId, String text);

    List<ProductDTO> findAll();


    <S extends Product> S save(S entity);

    Page<ProductDTO> findAll(Pageable pageable);

    Optional<Product> findById(Integer integer);

    ListProductResponse getProductByCategory(Pageable pageable);
}
