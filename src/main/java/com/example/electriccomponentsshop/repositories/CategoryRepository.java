package com.example.electriccomponentsshop.repositories;

import com.example.electriccomponentsshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
  List<Category> findCategoriesByParentCategoryIdIsNull();



  @Override
  <S extends Category> S save(S entity);

  @Override
  List<Category> findAll();
}
