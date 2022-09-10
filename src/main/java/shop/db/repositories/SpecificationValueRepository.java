package shop.db.repositories;

import shop.db.entities.SpecificationValue;
import shop.db.entities.SpecificationValueId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecificationValueRepository extends JpaRepository<SpecificationValue, SpecificationValueId> {
    @Override
    List<SpecificationValue> findAll();
    void deleteSpecificationValuesByProductId(Integer pId);

}
