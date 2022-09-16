package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.ExportTransaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExportTransactionRepository extends JpaRepository<ExportTransaction,Integer> {
}
