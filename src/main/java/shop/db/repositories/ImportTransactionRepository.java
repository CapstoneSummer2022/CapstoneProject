package shop.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.db.entities.ImportTransaction;

public interface ImportTransactionRepository extends JpaRepository<ImportTransaction,Integer> {
}
