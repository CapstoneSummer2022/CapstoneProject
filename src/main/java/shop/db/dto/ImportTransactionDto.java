package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.db.dto.ImportItemDto;
import shop.db.dto.WarehouseDTO;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportTransactionDto {
    private String importTransactionId;
    private String sku;
    private String supplierId;
    private List<ImportItemDto> importItems;
    private String importDate;
    private String warehouseId;
    private Double totalPayment;
}
