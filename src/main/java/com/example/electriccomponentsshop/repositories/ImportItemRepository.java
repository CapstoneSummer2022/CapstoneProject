package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.ImportItem;
import com.example.electriccomponentsshop.entities.ImportItemId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImportItemRepository extends JpaRepository<ImportItem, ImportItemId> {
}
