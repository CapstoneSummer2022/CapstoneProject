package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.entities.Warehouse;
import com.example.electriccomponentsshop.repositories.WarehouseRepository;
import com.example.electriccomponentsshop.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public List<Warehouse> findAll(Sort sort) {
        return warehouseRepository.findAll(sort);
    }

    @Override
    public List<Warehouse> findAllById(Iterable<Integer> integers) {
        return warehouseRepository.findAllById(integers);
    }

    @Override
    public <S extends Warehouse> List<S> saveAll(Iterable<S> entities) {
        return warehouseRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        warehouseRepository.flush();
    }

    @Override
    public <S extends Warehouse> S saveAndFlush(S entity) {
        return warehouseRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Warehouse> List<S> saveAllAndFlush(Iterable<S> entities) {
        return warehouseRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Warehouse> entities) {
        warehouseRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Warehouse> entities) {
        warehouseRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        warehouseRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        warehouseRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Warehouse getOne(Integer integer) {
        return warehouseRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public Warehouse getById(Integer integer) {
        return warehouseRepository.getById(integer);
    }

    @Override
    public Warehouse getReferenceById(Integer integer) {
        return warehouseRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Warehouse> List<S> findAll(Example<S> example) {
        return warehouseRepository.findAll(example);
    }

    @Override
    public <S extends Warehouse> List<S> findAll(Example<S> example, Sort sort) {
        return warehouseRepository.findAll(example, sort);
    }

    @Override
    public Page<Warehouse> findAll(Pageable pageable) {
        return warehouseRepository.findAll(pageable);
    }

    @Override
    public <S extends Warehouse> S save(S entity) {
        return warehouseRepository.save(entity);
    }

    @Override
    public Optional<Warehouse> findById(Integer integer) {
        return warehouseRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return warehouseRepository.existsById(integer);
    }

    @Override
    public long count() {
        return warehouseRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        warehouseRepository.deleteById(integer);
    }

    @Override
    public void delete(Warehouse entity) {
        warehouseRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        warehouseRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Warehouse> entities) {
        warehouseRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        warehouseRepository.deleteAll();
    }

    @Override
    public <S extends Warehouse> Optional<S> findOne(Example<S> example) {
        return warehouseRepository.findOne(example);
    }

    @Override
    public <S extends Warehouse> Page<S> findAll(Example<S> example, Pageable pageable) {
        return warehouseRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Warehouse> long count(Example<S> example) {
        return warehouseRepository.count(example);
    }

    @Override
    public <S extends Warehouse> boolean exists(Example<S> example) {
        return warehouseRepository.exists(example);
    }

    @Override
    public <S extends Warehouse, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return warehouseRepository.findBy(example, queryFunction);
    }
}
