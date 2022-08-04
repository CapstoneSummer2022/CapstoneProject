package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.entities.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<Account> findByEmail(String email);

    @Query(value = "select * from accounts join roles where role_name= :role", nativeQuery = true)
    List<Account> findAllByRoleName(String role);

    Optional<Account> findById(Integer id);

    Boolean existsAccountByEmail(String email);

    <S extends Account> S save(S entity);

    List<Account> findAll();

    List<Account> findAll(Sort sort);

    List<Account> findAllById(Iterable<Integer> integers);

    <S extends Account> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Account> S saveAndFlush(S entity);
}
