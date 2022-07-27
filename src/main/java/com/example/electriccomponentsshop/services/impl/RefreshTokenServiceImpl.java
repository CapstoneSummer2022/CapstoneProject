package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.entities.RefreshToken;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.repositories.RefreshTokenRepository;
import com.example.electriccomponentsshop.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Value("86400000")
    private long jwtRefreshExpirationMs;
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Optional<RefreshToken> findById(Integer integer) {
        return refreshTokenRepository.findById(integer);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public Optional<RefreshToken> findByAccount_Email(String email) {
        return refreshTokenRepository.findByAccount_Email(email);
    }

    @Override
    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccount(accountRepository.findByEmail(email).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(jwtRefreshExpirationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }
    @Override
    public boolean isExpiration(RefreshToken token) {
           if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
               return true;
           }
           else return false;




    }
    @Override
    public List<RefreshToken> findAll() {
        return refreshTokenRepository.findAll();
    }

    @Override
    public List<RefreshToken> findAll(Sort sort) {
        return refreshTokenRepository.findAll(sort);
    }

    @Override
    public List<RefreshToken> findAllById(Iterable<Integer> integers) {
        return refreshTokenRepository.findAllById(integers);
    }

    @Override
    public <S extends RefreshToken> List<S> saveAll(Iterable<S> entities) {
        return refreshTokenRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        refreshTokenRepository.flush();
    }

    @Override
    public <S extends RefreshToken> S saveAndFlush(S entity) {
        return refreshTokenRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends RefreshToken> List<S> saveAllAndFlush(Iterable<S> entities) {
        return refreshTokenRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<RefreshToken> entities) {
        refreshTokenRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<RefreshToken> entities) {
        refreshTokenRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        refreshTokenRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        refreshTokenRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public RefreshToken getOne(Integer integer) {
        return refreshTokenRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public RefreshToken getById(Integer integer) {
        return refreshTokenRepository.getById(integer);
    }

    @Override
    public RefreshToken getReferenceById(Integer integer) {
        return refreshTokenRepository.getReferenceById(integer);
    }

    @Override
    public <S extends RefreshToken> List<S> findAll(Example<S> example) {
        return refreshTokenRepository.findAll(example);
    }

    @Override
    public <S extends RefreshToken> List<S> findAll(Example<S> example, Sort sort) {
        return refreshTokenRepository.findAll(example, sort);
    }

    @Override
    public Page<RefreshToken> findAll(Pageable pageable) {
        return refreshTokenRepository.findAll(pageable);
    }

    @Override
    public <S extends RefreshToken> S save(S entity) {
        return refreshTokenRepository.save(entity);
    }

    @Override
    public boolean existsById(Integer integer) {
        return refreshTokenRepository.existsById(integer);
    }

    @Override
    public long count() {
        return refreshTokenRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        refreshTokenRepository.deleteById(integer);
    }

    @Override
    public void delete(RefreshToken entity) {
        refreshTokenRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        refreshTokenRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends RefreshToken> entities) {
        refreshTokenRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        refreshTokenRepository.deleteAll();
    }

    @Override
    public <S extends RefreshToken> Optional<S> findOne(Example<S> example) {
        return refreshTokenRepository.findOne(example);
    }

    @Override
    public <S extends RefreshToken> Page<S> findAll(Example<S> example, Pageable pageable) {
        return refreshTokenRepository.findAll(example, pageable);
    }

    @Override
    public <S extends RefreshToken> long count(Example<S> example) {
        return refreshTokenRepository.count(example);
    }

    @Override
    public <S extends RefreshToken> boolean exists(Example<S> example) {
        return refreshTokenRepository.exists(example);
    }

    @Override
    public <S extends RefreshToken, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return refreshTokenRepository.findBy(example, queryFunction);
    }
}
