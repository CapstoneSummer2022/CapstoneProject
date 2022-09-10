package shop.services.impl;

import shop.common.ERole;
import shop.db.dto.RoleDTO;
import shop.db.entities.Role;
import shop.db.repositories.RoleRepository;
import shop.services.RoleService;
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
