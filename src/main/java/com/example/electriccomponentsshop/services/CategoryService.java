package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> findCategoriesByParentCategoryIdIsNull();

    List<CategoryDTO> findCategoriesByIdNotIn(List<CategoryDTO> cId);

    Category getById(String id);

    List<Category> getAllSub (int id);

    <S extends Category> S save(S entity);

    List<CategoryDTO> findAllSubAndParCategories(Integer cId);

    List<CategoryDTO> findAll();

    Page<Category> findAll(Pageable pageable);
    boolean addCategory(CategoryDTO categoryDTO);
    boolean updateCategory(CategoryDTO categoryDTO,String strId);
    CategoryDTO findById(Integer id);
    boolean existsById(Integer integer);
    boolean removeChildCategory(Category child);
    boolean addChildCategory(Category child,Category parent);
    long count();
}
