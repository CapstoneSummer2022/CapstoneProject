package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.helper.ExcelHelper;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class ExcelService {
    ProductRepository repository;
    public void save(MultipartFile file) {
        try {
            List<Product> products = ExcelHelper.excelToProduct(file.getInputStream());
            repository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("Fail to store excel data: " + e.getMessage());
        }
    }
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
