package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportItemDto {
    private String productId;
    private String skuCode;
    private Integer importPrice;
    private Integer quantity;
    private String importTransactionId;
    private String containerId;
}
