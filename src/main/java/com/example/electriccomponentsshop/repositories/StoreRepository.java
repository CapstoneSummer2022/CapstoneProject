package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.StoreInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreInformation, Integer> {
}
