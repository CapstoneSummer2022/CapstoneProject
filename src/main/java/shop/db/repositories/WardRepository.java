package shop.db.repositories;

import shop.db.entities.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    @Override
    List<Ward> findAll();
    Optional<Ward> findByName(String name);
    List<Ward> findByDistrictName(String districtName);
}
