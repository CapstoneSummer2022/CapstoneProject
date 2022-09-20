package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExportTransactionDto {
    private String id;
    private String exportDate;
    private String orderId;
    private List<ExportItemDto> exportItems;
    private String exportPerson;
    private String receivedPerson;
    private String description;

}
