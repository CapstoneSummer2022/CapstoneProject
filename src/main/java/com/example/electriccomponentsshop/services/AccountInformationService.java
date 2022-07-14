package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.entities.AccountInformation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface AccountInformationService {
    Optional<AccountInformation> findAccountInformationById(int id);

    <S extends AccountInformation> S save(S entity);

    List<AccountInformation> findAll();

    List<AccountInformation> findAll(Sort sort);

    List<AccountInformation> findAllById(Iterable<Integer> integers);

    <S extends AccountInformation> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends AccountInformation> S saveAndFlush(S entity);

    <S extends AccountInformation> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<AccountInformation> entities);

    void deleteAllInBatch(Iterable<AccountInformation> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    AccountInformation getOne(Integer integer);

    @Deprecated
    AccountInformation getById(Integer integer);

    AccountInformation getReferenceById(Integer integer);

    <S extends AccountInformation> List<S> findAll(Example<S> example);

    <S extends AccountInformation> List<S> findAll(Example<S> example, Sort sort);

    Page<AccountInformation> findAll(Pageable pageable);

    Optional<AccountInformation> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(AccountInformation entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends AccountInformation> entities);

    void deleteAll();

    <S extends AccountInformation> Optional<S> findOne(Example<S> example);

    <S extends AccountInformation> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends AccountInformation> long count(Example<S> example);

    <S extends AccountInformation> boolean exists(Example<S> example);

    <S extends AccountInformation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
