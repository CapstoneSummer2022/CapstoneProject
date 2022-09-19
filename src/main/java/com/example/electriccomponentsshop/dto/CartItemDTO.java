package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private ProductDTO productDTO;

    private BigDecimal quantity;

    private BigDecimal subTotal;
}
