package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.entities.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface ProductService {

    int countByCate(String cate);

    List<ProductDTO> getProductByCate(String cate, int pageNo, int pageSize);

    ProductDTO convertToDto(Product product);

    ProductDTO getProductDtoById(String id);

    Product getById(String id);

    List<ProductDTO> findAll();

    Page<Product> findAll(Pageable pageable);

    <S extends Product> S save(S entity);

    List<Product> findAll(Sort sort);

    List<Product> findAllById(Iterable<Integer> integers);

    <S extends Product> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Product> S saveAndFlush(S entity);

    <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Product> entities);

    void deleteAllInBatch(Iterable<Product> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    Product getOne(Integer integer);

    @Deprecated
    Product getById(Integer integer);

    Product getReferenceById(Integer integer);

    <S extends Product> List<S> findAll(Example<S> example);

    <S extends Product> List<S> findAll(Example<S> example, Sort sort);

    Optional<Product> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Product entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Product> entities);

    void deleteAll();

    <S extends Product> Optional<S> findOne(Example<S> example);

    <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Product> long count(Example<S> example);

    <S extends Product> boolean exists(Example<S> example);

    <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
