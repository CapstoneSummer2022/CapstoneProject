package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findCategoriesByParentCategoryIdIsNull();

    <S extends Category> S save(S entity);

    List<Category> findAll();

    List<Category> findAllById(Iterable<Integer> integers);

    <S extends Category> List<S> saveAll(Iterable<S> entities);

    @Deprecated
    Category getOne(Integer integer);

    @Deprecated
    Category getById(Integer integer);

    Category getReferenceById(Integer integer);

    Page<Category> findAll(Pageable pageable);

    Category findById(Integer integer);

    boolean existsById(Integer integer);

    long count();
}
