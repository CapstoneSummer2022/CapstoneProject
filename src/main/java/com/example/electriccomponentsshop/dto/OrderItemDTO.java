package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private String orderId;
    private String productId;
    private String productName;
    private String productImage;
    private BigDecimal subTotal;
    private BigDecimal quantity;
    private String unitPrice;
}
