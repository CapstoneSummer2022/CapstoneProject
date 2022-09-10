package shop.db.repositories;

import shop.db.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Override
    List<Supplier> findAll();

    @Override
    <S extends Supplier> S save(S entity);
}