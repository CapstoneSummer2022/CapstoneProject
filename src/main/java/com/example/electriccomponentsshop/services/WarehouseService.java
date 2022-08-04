package com.example.electriccomponentsshop.services;

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

    List<Warehouse> findAll(Sort sort);

    List<Warehouse> findAllById(Iterable<Integer> integers);

    <S extends Warehouse> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Warehouse> S saveAndFlush(S entity);

    <S extends Warehouse> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Warehouse> entities);

    void deleteAllInBatch(Iterable<Warehouse> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    Warehouse getOne(Integer integer);

    @Deprecated
    Warehouse getById(Integer integer);

    Warehouse getReferenceById(Integer integer);

    <S extends Warehouse> List<S> findAll(Example<S> example);

    <S extends Warehouse> List<S> findAll(Example<S> example, Sort sort);

    Page<Warehouse> findAll(Pageable pageable);

    <S extends Warehouse> S save(S entity);

    Optional<Warehouse> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Warehouse entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Warehouse> entities);

    void deleteAll();

    <S extends Warehouse> Optional<S> findOne(Example<S> example);

    <S extends Warehouse> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Warehouse> long count(Example<S> example);

    <S extends Warehouse> boolean exists(Example<S> example);

    <S extends Warehouse, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
