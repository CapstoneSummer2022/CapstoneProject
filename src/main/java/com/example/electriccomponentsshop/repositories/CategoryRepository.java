package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
  List<Category> findCategoriesByParentCategoryIdIsNull();
  @Query(value="WITH RECURSIVE ALL_SUBCATEGORIES(ID,Name, PARENTID) AS (select c.id,c.name,c.parent_category_id from category  c where c.id = :cId union all select c.id,c.name, c.parent_category_id from ALL_SUBCATEGORIES inner join category c on ALL_SUBCATEGORIES.id = c.parent_category_id) select id,name,parentid from ALL_SUBCATEGORIES",nativeQuery = true)
  List<Category> findAllSubCategories(@Param("cId") Integer cId);

  @Override
  <S extends Category> S save(S entity);

  @Override
  List<Category> findAll();
}
