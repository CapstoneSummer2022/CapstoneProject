package com.example.electriccomponentsshop.dto;


import com.example.electriccomponentsshop.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListProductResponse {
    private List<ProductDTO> productDTOS = new ArrayList<>();
    private int totalPages;
}
