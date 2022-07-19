package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountInformationRepository extends JpaRepository<UserInformation,Integer> {
    //Optional<AccountInformation> findAccountInformationByEmail();

    Optional<UserInformation> findAccountInformationById(int id);

    @Override
    <S extends UserInformation> S save(S entity);
}
