package shop.services;

import shop.db.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> findAll();

    <S extends Supplier> S save(S entity);

    Optional<Supplier> findById(Integer integer);
}
