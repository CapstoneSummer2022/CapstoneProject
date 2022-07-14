package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.repositories.CategoryRepository;
import com.example.electriccomponentsshop.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findCategoriesByParentCategoryIdIsNull() {
        return categoryRepository.findCategoriesByParentCategoryIdIsNull();
    }

    @Override
    public <S extends Category> S save(S entity) {

        return categoryRepository.save(entity);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    @Override
    public List<Category> findAllById(Iterable<Integer> integers) {
        return categoryRepository.findAllById(integers);
    }

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categoryRepository.saveAll(entities);
    }



    @Override
    @Deprecated
    public Category getOne(Integer integer) {
        return categoryRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public Category getById(Integer integer) {
        return categoryRepository.getById(integer);
    }

    @Override
    public Category getReferenceById(Integer integer) {
        return categoryRepository.getReferenceById(integer);
    }



    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category findById(Integer integer) throws NoSuchElementException {
       return categoryRepository.findById(integer).get();

    }

    @Override
    public boolean existsById(Integer integer) {
        return categoryRepository.existsById(integer);
    }

    @Override
    public long count() {
        return categoryRepository.count();
    }


}
