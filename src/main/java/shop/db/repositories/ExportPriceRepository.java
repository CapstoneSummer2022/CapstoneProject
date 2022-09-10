package shop.db.repositories;

import shop.db.entities.ExportPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExportPriceRepository extends JpaRepository<ExportPrice,Integer> {
    Optional<ExportPrice> findByProductId(Integer productId);
}
