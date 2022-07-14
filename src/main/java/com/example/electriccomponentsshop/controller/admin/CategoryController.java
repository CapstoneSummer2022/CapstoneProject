package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("admin/categories")
public class CategoryController  {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ModelMap modelMap;
    @GetMapping("/list")
    public String list(){
        return "admin/categories/list";

    }
    @PostMapping("/add")
    public String addCategory(@Validated @RequestBody CategoryDTO categoryDTO){
        Category category = new Category(categoryDTO.getName());
        if(categoryDTO.getParentId()==null){
            categoryService.save(category);
        }
        else if(categoryDTO.getParentId()!=null) {
            try {
                Category c  = categoryService.findById(categoryDTO.getParentId());
                category.setParentCategory(c);
                Set<Category> childCategories = c.getChildCategories();
                childCategories.add(category);
                c.setChildCategories(childCategories);
                categoryService.save(category);
            }catch (NoSuchElementException e){
                return "Not found parent category";
            }
        }
        return "ok";




    }
    public Category convertToEntity(CategoryDTO categoryDTO){
        Category category = modelMap.modelMapper().map(categoryDTO,Category.class);
        return category;
    }
    public CategoryDTO convertToDTO(Category category){
        CategoryDTO categoryDTO = modelMap.modelMapper().map(category,CategoryDTO.class);
        return categoryDTO;
    }
}
