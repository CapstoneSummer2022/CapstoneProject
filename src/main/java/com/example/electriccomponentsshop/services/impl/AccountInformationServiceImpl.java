package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.entities.AccountInformation;
import com.example.electriccomponentsshop.repositories.AccountInformationRepository;
import com.example.electriccomponentsshop.services.AccountInformationService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AccountInformationServiceImpl implements AccountInformationService {
    AccountInformationRepository accountInformationRepository;

    public AccountInformationServiceImpl(AccountInformationRepository accountInformationRepository) {
        this.accountInformationRepository = accountInformationRepository;
    }

    @Override
    public Optional<AccountInformation> findAccountInformationById(int id) {
        return accountInformationRepository.findAccountInformationById(id);
    }

    @Override
    public <S extends AccountInformation> S save(S entity) {
        return accountInformationRepository.save(entity);
    }

    @Override
    public List<AccountInformation> findAll() {
        return accountInformationRepository.findAll();
    }

    @Override
    public List<AccountInformation> findAll(Sort sort) {
        return accountInformationRepository.findAll(sort);
    }

    @Override
    public List<AccountInformation> findAllById(Iterable<Integer> integers) {
        return accountInformationRepository.findAllById(integers);
    }

    @Override
    public <S extends AccountInformation> List<S> saveAll(Iterable<S> entities) {
        return accountInformationRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        accountInformationRepository.flush();
    }

    @Override
    public <S extends AccountInformation> S saveAndFlush(S entity) {
        return accountInformationRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends AccountInformation> List<S> saveAllAndFlush(Iterable<S> entities) {
        return accountInformationRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<AccountInformation> entities) {
        accountInformationRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<AccountInformation> entities) {
        accountInformationRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        accountInformationRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        accountInformationRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public AccountInformation getOne(Integer integer) {
        return accountInformationRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public AccountInformation getById(Integer integer) {
        return accountInformationRepository.getById(integer);
    }

    @Override
    public AccountInformation getReferenceById(Integer integer) {
        return accountInformationRepository.getReferenceById(integer);
    }

    @Override
    public <S extends AccountInformation> List<S> findAll(Example<S> example) {
        return accountInformationRepository.findAll(example);
    }

    @Override
    public <S extends AccountInformation> List<S> findAll(Example<S> example, Sort sort) {
        return accountInformationRepository.findAll(example, sort);
    }

    @Override
    public Page<AccountInformation> findAll(Pageable pageable) {
        return accountInformationRepository.findAll(pageable);
    }

    @Override
    public Optional<AccountInformation> findById(Integer integer) {
        return accountInformationRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return accountInformationRepository.existsById(integer);
    }

    @Override
    public long count() {
        return accountInformationRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        accountInformationRepository.deleteById(integer);
    }

    @Override
    public void delete(AccountInformation entity) {
        accountInformationRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        accountInformationRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends AccountInformation> entities) {
        accountInformationRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        accountInformationRepository.deleteAll();
    }

    @Override
    public <S extends AccountInformation> Optional<S> findOne(Example<S> example) {
        return accountInformationRepository.findOne(example);
    }

    @Override
    public <S extends AccountInformation> Page<S> findAll(Example<S> example, Pageable pageable) {
        return accountInformationRepository.findAll(example, pageable);
    }

    @Override
    public <S extends AccountInformation> long count(Example<S> example) {
        return accountInformationRepository.count(example);
    }

    @Override
    public <S extends AccountInformation> boolean exists(Example<S> example) {
        return accountInformationRepository.exists(example);
    }

    @Override
    public <S extends AccountInformation, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return accountInformationRepository.findBy(example, queryFunction);
    }
}
