package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private ProductDTO productDTO;

    private BigInteger quantity;

    private BigDecimal subTotal;
}
