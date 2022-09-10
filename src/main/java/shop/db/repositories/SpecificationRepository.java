package shop.db.repositories;

import shop.db.entities.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationRepository extends JpaRepository<Specification,Integer> {
    List<Specification> findSpecificationsByIdNotIn(Integer... sId);
}
