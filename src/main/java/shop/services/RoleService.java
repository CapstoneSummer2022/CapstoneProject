package shop.services;

import shop.common.ERole;
import shop.db.dto.RoleDTO;
import shop.db.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRoleName(ERole roleName);

    List<RoleDTO> findAll();

}
