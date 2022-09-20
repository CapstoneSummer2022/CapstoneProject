package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.ExportTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExportTransactionRepository extends JpaRepository<ExportTransaction,Integer> {
    ExportTransaction findExportTransactionByOrderId(Integer id);
}
