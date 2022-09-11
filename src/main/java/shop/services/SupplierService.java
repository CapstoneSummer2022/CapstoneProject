package shop.services;

import shop.db.dto.SupplierDTO;
import shop.db.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> findAll();

    <S extends Supplier> S save(S entity);

    SupplierDTO convertToDto(Supplier supplier);

    Supplier getBySupplierId(String id);

    List<SupplierDTO> getAllSupplier();

    Optional<Supplier> findById(Integer integer);
}
