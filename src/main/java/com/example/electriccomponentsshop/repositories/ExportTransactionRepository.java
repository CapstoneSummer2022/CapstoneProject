package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.ExportTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ExportTransactionRepository extends JpaRepository<ExportTransaction,Integer> {
    Optional<ExportTransaction> findExportTransactionByOrderId(Integer id);
}
