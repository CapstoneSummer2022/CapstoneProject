package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal subTotal;
    private BigInteger quantity;
    private BigDecimal unitPrice;
    private String productImage;
}
