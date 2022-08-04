package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.repositories.SupplierRepository;
import com.example.electriccomponentsshop.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Override
    public <S extends Supplier> S save(S entity) {
        return supplierRepository.save(entity);
    }

    @Override
    public Optional<Supplier> findById(Integer integer) {
        return supplierRepository.findById(integer);
    }
}
