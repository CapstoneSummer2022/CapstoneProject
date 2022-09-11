package shop.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.db.entities.ImportItem;

public interface ImportItemRepository extends JpaRepository<ImportItem,Integer> {
}
