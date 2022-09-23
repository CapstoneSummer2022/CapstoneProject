package com.example.electriccomponentsshop;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.services.CategoryService;
import com.example.electriccomponentsshop.services.ProductService;
import com.example.electriccomponentsshop.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;
    @Mock
    ModelMap modelMap;

    @Test
    void whenGetAll_shouldReturnList() {
        // 1. create mock data
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            products.add(new Product(i, "product " + i, "A11.jpg", "mô tả", new BigInteger("500"), 1, new BigInteger("1")));
        }

        // 2. define behavior of Repository
        when(productRepository.findAll()).thenReturn(products);

        // 3. call service method
        List<Product> actualProduct = productService.getAll();

        // 4. assert the result
        assertThat(actualProduct.size()).isEqualTo(products.size());

        // 4.1 ensure repository is called
        verify(productRepository).findAll();
    }
}


