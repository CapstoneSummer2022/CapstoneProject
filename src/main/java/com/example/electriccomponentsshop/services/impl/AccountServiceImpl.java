package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    @Query(value = "select * from accounts join roles where role_name= :role", nativeQuery = true)
    public List<Account> findAllByRoleName(String role) {
        return accountRepository.findAllByRoleName(role);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Boolean existsAccountByEmail(String email) {
        return accountRepository.existsAccountByEmail(email);
    }

    @Override
    public <S extends Account> S save(S entity) {
        return accountRepository.save(entity);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAll(Sort sort) {
        return accountRepository.findAll(sort);
    }

    @Override
    public List<Account> findAllById(Iterable<Integer> integers) {
        return accountRepository.findAllById(integers);
    }

    @Override
    public <S extends Account> List<S> saveAll(Iterable<S> entities) {
        return accountRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        accountRepository.flush();
    }

    @Override
    public <S extends Account> S saveAndFlush(S entity) {
        return accountRepository.saveAndFlush(entity);
    }

    public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountRepository.saveAllAndFlush(entities);
    }

    @Deprecated
    public void deleteInBatch(Iterable<Account> entities) {
        accountRepository.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Account> entities) {
        accountRepository.deleteAllInBatch(entities);
    }

    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        accountRepository.deleteAllByIdInBatch(integers);
    }

    public void deleteAllInBatch() {
        accountRepository.deleteAllInBatch();
    }

    @Deprecated
    public Account getOne(Integer integer) {
        return accountRepository.getOne(integer);
    }

    @Deprecated
    public Account getById(Integer integer) {
        return accountRepository.getById(integer);
    }

    public Account getReferenceById(Integer integer) {
        return accountRepository.getReferenceById(integer);
    }

    public <S extends Account> List<S> findAll(Example<S> example) {
        return accountRepository.findAll(example);
    }

    public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
        return accountRepository.findAll(example, sort);
    }

    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public boolean existsById(Integer integer) {
        return accountRepository.existsById(integer);
    }

    public long count() {
        return accountRepository.count();
    }

    public void deleteById(Integer integer) {
        accountRepository.deleteById(integer);
    }

    public void delete(Account entity) {
        accountRepository.delete(entity);
    }

    public void deleteAllById(Iterable<? extends Integer> integers) {
        accountRepository.deleteAllById(integers);
    }

    public void deleteAll(Iterable<? extends Account> entities) {
        accountRepository.deleteAll(entities);
    }

    public void deleteAll() {
        accountRepository.deleteAll();
    }

    public <S extends Account> Optional<S> findOne(Example<S> example) {
        return accountRepository.findOne(example);
    }

    public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountRepository.findAll(example, pageable);
    }

    public <S extends Account> long count(Example<S> example) {
        return accountRepository.count(example);
    }

    public <S extends Account> boolean exists(Example<S> example) {
        return accountRepository.exists(example);
    }

    public <S extends Account, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return accountRepository.findBy(example, queryFunction);
    }
}
