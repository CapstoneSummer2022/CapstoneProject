package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Override
    Optional<Role> findById(Integer id);
    Optional<Role> findByRoleName(ERole roleName);
}