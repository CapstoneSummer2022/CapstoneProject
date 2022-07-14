package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountInformationRepository extends JpaRepository<AccountInformation,Integer> {
    //Optional<AccountInformation> findAccountInformationByEmail();

    Optional<AccountInformation> findAccountInformationById(int id);

    @Override
    <S extends AccountInformation> S save(S entity);
}
