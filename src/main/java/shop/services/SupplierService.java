package shop.services;

<<<<<<< HEAD:src/main/java/shop/services/SupplierService.java
import shop.db.entities.Supplier;
=======
import com.example.electriccomponentsshop.dto.SupplierDTO;
import com.example.electriccomponentsshop.entities.Supplier;
>>>>>>> 9e7c550 (ImportTransaction):src/main/java/com/example/electriccomponentsshop/services/SupplierService.java

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
