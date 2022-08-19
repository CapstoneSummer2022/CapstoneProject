package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.entities.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    AccountDTO findByEmail(String email) ;

    boolean addAccount(AccountDTO accountDTO);
    AccountDTO convertToDto(Account account) ;
    boolean updateAccount(AccountDTO accountDTO,Integer id);
    List<AccountDTO> findAllByRoleName(String... role) ;

    AccountDTO findById(Integer id);

    Boolean existsAccountByEmail(String email);

    <S extends Account> S save(S entity);

    List<AccountDTO> findAll();

    List<Account> findAll(Sort sort);

    List<Account> findAllById(Iterable<Integer> integers);

    <S extends Account> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Account> S saveAndFlush(S entity);
}
