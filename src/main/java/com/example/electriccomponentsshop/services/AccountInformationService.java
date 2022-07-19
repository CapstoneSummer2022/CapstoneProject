package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.entities.UserInformation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface AccountInformationService {
    Optional<UserInformation> findAccountInformationById(int id);

    <S extends UserInformation> S save(S entity);

    List<UserInformation> findAll();

    List<UserInformation> findAll(Sort sort);

    List<UserInformation> findAllById(Iterable<Integer> integers);

    <S extends UserInformation> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends UserInformation> S saveAndFlush(S entity);

    <S extends UserInformation> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<UserInformation> entities);

    void deleteAllInBatch(Iterable<UserInformation> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    UserInformation getOne(Integer integer);

    @Deprecated
    UserInformation getById(Integer integer);

    UserInformation getReferenceById(Integer integer);

    <S extends UserInformation> List<S> findAll(Example<S> example);

    <S extends UserInformation> List<S> findAll(Example<S> example, Sort sort);

    Page<UserInformation> findAll(Pageable pageable);

    Optional<UserInformation> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(UserInformation entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends UserInformation> entities);

    void deleteAll();

    <S extends UserInformation> Optional<S> findOne(Example<S> example);

    <S extends UserInformation> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends UserInformation> long count(Example<S> example);

    <S extends UserInformation> boolean exists(Example<S> example);

    <S extends UserInformation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
