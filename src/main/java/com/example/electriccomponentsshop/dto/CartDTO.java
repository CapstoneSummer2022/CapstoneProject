package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private ProductDTO product;
    private int quantity;
    private double subTotal;

}
