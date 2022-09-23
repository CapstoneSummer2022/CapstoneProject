package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.ImportTransactionDto;
import com.example.electriccomponentsshop.entities.ImportTransaction;


import java.util.List;
import java.util.NoSuchElementException;


public interface ImportTransactionService {

    ImportTransactionDto convertToDto(ImportTransaction importTransaction);

    ImportTransactionDto getDtoById(String id);

    ImportTransaction getById(String id);

    List<ImportTransactionDto> findAll();

    boolean addImportTransaction(ImportTransactionDto importTransactionDto) throws NoSuchElementException;
}