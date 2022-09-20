package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportTransactionDto {
    private String id;
    private String supplierId;
    private String supplierName;
    private List<ImportItemDto> importItems;
    private String importDate;
    private String warehouseId;
    private String provinceName;
    private String districtName;
    private String wardName;
    private BigDecimal totalPayment;
}
