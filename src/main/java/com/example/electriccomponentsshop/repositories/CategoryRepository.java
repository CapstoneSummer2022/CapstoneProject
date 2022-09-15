package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findCategoriesByParentCategoryIdIsNull();
    List<Category> findCategoriesByIdNotIn(Integer... cId);
    @Query(value = "" +
            "select * from (with recursive allC(ID,NAME,parent_category_id) AS (select c.id,c.name,c.parent_category_id from category  c where c.id = :cId),sub as (select  * from allC a union select c.* from sub s inner join category c on s.id = c.parent_category_id),chi as(select * from allC  a union select c.* from chi ch inner join category c on ch.parent_category_id = c.id)\nselect distinct * from category where category.id not in( select t.id from (select distinct * from sub union all select distinct * from chi) t)) alias", nativeQuery = true)
    List<Category> findAllSubAndParCategories(@Param("cId") Integer cId);

    @Query(value = "select * from category where category.id not in (1,2)", nativeQuery = true)
    List<Category> findEx();

    @Query(value = "select * from category c where category.parent_id = :id", nativeQuery = true)
    List<Category> findSubcategories(@Param("id") Integer id);

    @Override
    <S extends Category> S save(S entity);

    @Override
    List<Category> findAll();
}
