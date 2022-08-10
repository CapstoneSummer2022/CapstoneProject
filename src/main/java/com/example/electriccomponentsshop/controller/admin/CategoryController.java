package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.services.CategoryService;
import com.example.electriccomponentsshop.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/admin/categories")
public class CategoryController  {
    final
    CategoryService categoryService;

    final
    ModelMap modelMap;

    public CategoryController(CategoryService categoryService, ModelMap modelMap) {
        this.categoryService = categoryService;
        this.modelMap = modelMap;
    }

    @GetMapping("")
    public String list(Model model,CategoryDTO categoryDTO){
        ArrayList<Category> categories = (ArrayList<Category>)categoryService.findAll();
        System.out.println(categories.isEmpty());
        model.addAttribute("categories", categories);
        return "administrator/category-management";

    }
    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("categoryDTO") CategoryDTO categoryDTO,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            System.out.println("ff");
        }
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
        return "redirect:/list";

    }
    @PostMapping("category/update/{id}")
    public ModelAndView editCategory(@PathVariable int id, @Valid @ModelAttribute("category1") CategoryDTO categoryDTO, BindingResult bindingResult){
      try{

          Category category = categoryService.findById(id);
          if(category.getParentCategory()==null){
              category.setName(categoryDTO.getName());
              categoryService.save(category);
          }
          else if(category.getParentCategory()!=null){
              Category parent = categoryService.findById(categoryDTO.getParentId());
              category.setParentCategory(parent);
              Set<Category> childCategories = parent.getChildCategories();
              childCategories.add(category);
              categoryService.save(category);
          }
      }
      catch(NoSuchElementException e){
            return new ModelAndView("Không tìm thấy category");

      }
        return new ModelAndView("category");
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
