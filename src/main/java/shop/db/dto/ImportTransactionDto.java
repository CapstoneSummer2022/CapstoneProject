package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportTransactionDto {
    private String sku;
    private String supplierId;
    private List<ImportItemDto> importItems;
    private String importDate;
    private WarehouseDTO warehouseDTO;
}
