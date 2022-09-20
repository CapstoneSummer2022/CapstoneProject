package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportItemDto {
    private String productId;
    private String skuId;
    private BigDecimal importPrice;
    private BigDecimal quantity;
    private String importTransactionId;
    private String containerId;
}
