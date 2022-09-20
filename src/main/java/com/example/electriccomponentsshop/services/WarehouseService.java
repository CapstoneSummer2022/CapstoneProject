package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.WarehouseDTO;
import com.example.electriccomponentsshop.entities.Warehouse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface WarehouseService {
    List<Warehouse> findAll();


    WarehouseDTO convertToDto(Warehouse warehouse);

    List<WarehouseDTO>  getAllWarehouse();

    Warehouse getWarehouse(String id);

    Page<Warehouse> findAll(Pageable pageable);
    <S extends Warehouse> S save(S entity);
    Optional<Warehouse> findById(Integer integer);
    void add(WarehouseDTO warehouseDTO);

    void update(WarehouseDTO warehouseDTO,String id);

    WarehouseDTO getWarehouseDtoById(String id);

    void disableWarehouse(String id);

    void enableWarehouse(String id);
}
