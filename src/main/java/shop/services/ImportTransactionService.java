package shop.services;

import shop.db.entities.ImportTransaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface ImportTransactionService {

    com.example.electriccomponentsshop.dto.ImportTransactionDto convertToDto(ImportTransaction importTransaction);

    List<com.example.electriccomponentsshop.dto.ImportTransactionDto> findAll();

    boolean addImportTransaction(com.example.electriccomponentsshop.dto.ImportTransactionDto importTransactionDto) throws NoSuchElementException;
}
