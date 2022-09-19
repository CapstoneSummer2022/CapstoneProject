package com.example.electriccomponentsshop.services;


import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.dto.RoleDTO;
import com.example.electriccomponentsshop.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRoleName(ERole roleName);

    List<RoleDTO> findAll();

}
