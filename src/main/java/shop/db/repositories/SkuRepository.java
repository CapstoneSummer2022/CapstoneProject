package shop.db.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import shop.db.entities.Sku;

public interface SkuRepository extends JpaRepository<Sku,Integer> {

}
