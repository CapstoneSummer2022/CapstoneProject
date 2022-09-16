package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.dto.RoleDTO;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.repositories.RoleRepository;
import com.example.electriccomponentsshop.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRoleName(ERole roleName) {
     return roleRepository.findByRoleName(roleName);

    }
    public RoleDTO convertToDto(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName(role.getRoleName().name());
        return roleDTO;
    }
    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role r: roles
             ) {
            roleDTOS.add(convertToDto(r));
        }
        return roleDTOS;
    }
}
