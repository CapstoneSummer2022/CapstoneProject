package shop.db.repositories;

import shop.db.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Override
    List<Province> findAll();
    Optional<Province> findByName(String name);
}
