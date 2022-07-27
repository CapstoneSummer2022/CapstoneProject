package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.entities.RefreshToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface RefreshTokenService {
    Optional<RefreshToken> findById(Integer integer);

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByAccount_Email(String email);
    RefreshToken createRefreshToken(String email);

    boolean isExpiration(RefreshToken token);

    List<RefreshToken> findAll();

    List<RefreshToken> findAll(Sort sort);

    List<RefreshToken> findAllById(Iterable<Integer> integers);

    <S extends RefreshToken> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends RefreshToken> S saveAndFlush(S entity);

    <S extends RefreshToken> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<RefreshToken> entities);

    void deleteAllInBatch(Iterable<RefreshToken> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();

    @Deprecated
    RefreshToken getOne(Integer integer);

    @Deprecated
    RefreshToken getById(Integer integer);

    RefreshToken getReferenceById(Integer integer);

    <S extends RefreshToken> List<S> findAll(Example<S> example);

    <S extends RefreshToken> List<S> findAll(Example<S> example, Sort sort);

    Page<RefreshToken> findAll(Pageable pageable);

    <S extends RefreshToken> S save(S entity);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(RefreshToken entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends RefreshToken> entities);

    void deleteAll();

    <S extends RefreshToken> Optional<S> findOne(Example<S> example);

    <S extends RefreshToken> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends RefreshToken> long count(Example<S> example);

    <S extends RefreshToken> boolean exists(Example<S> example);

    <S extends RefreshToken, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
