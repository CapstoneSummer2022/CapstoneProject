package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.ExportItem;
import com.example.electriccomponentsshop.entities.ExportItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportItemRepository extends JpaRepository<ExportItem, ExportItemId> {
    void deleteExportItemsByExportTransactionId(Integer eId);
}
