package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.ExportTransactionDto;
import com.example.electriccomponentsshop.entities.ExportTransaction;


import java.util.List;
import java.util.NoSuchElementException;

public interface ExportTransactionService {
    ExportTransactionDto convertToDto(ExportTransaction exportTransaction);

    ExportTransactionDto getDtoById(String id);

    ExportTransaction getById(String id);

    List<ExportTransactionDto> findAll();

    void addExportTransaction(ExportTransactionDto exportTransactionDto) throws NoSuchElementException;

    void updateExportTransaction(ExportTransactionDto exportTransactionDto) throws NoSuchElementException;
}
