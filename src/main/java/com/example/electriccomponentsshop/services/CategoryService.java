package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findCategoriesByIdNotIn(List<CategoryDTO> cId);

    Category getById(String id);

    List<CategoryDTO> findAllSubAndParCategories(Integer cId);

    List<CategoryDTO> findAll();

    Page<Category> findAll(Pageable pageable);

    boolean addCategory(CategoryDTO categoryDTO);

    boolean updateCategory(CategoryDTO categoryDTO, String strId);

    CategoryDTO findById(Integer id);
}
