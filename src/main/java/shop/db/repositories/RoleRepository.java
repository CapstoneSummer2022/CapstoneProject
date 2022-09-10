package shop.db.repositories;

import shop.common.ERole;
import shop.db.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Override
    Optional<Role> findById(Integer id);
    Optional<Role> findByRoleName(ERole roleName);

    @Override
    List<Role> findAll();
}
