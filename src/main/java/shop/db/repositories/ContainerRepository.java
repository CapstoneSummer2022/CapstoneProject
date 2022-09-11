package shop.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.db.entities.Container;

public interface ContainerRepository extends JpaRepository<Container,Integer> {

}
