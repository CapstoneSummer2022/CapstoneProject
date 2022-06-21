package com.example.electriccomponentsshop.repositories;


import com.example.electriccomponentsshop.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    /**
     * Find account by email
     * @param email
     * @return Account
     */
    Optional<Account> findByEmail(String email);
    /**
     * Check exist account with email
     *
     */
    Boolean existsAccountByEmail(String email);

}
