package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.repositories.CategoryRepository;
import com.example.electriccomponentsshop.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/admin/categories")
@AllArgsConstructor
public class CategoryController  {
    final
    CategoryService categoryService;
    final
    ModelMap modelMap;
    @GetMapping("")
    public String list(Model model){
        ArrayList<CategoryDTO> categories = (ArrayList<CategoryDTO>)categoryService.findAll();
        model.addAttribute("categories", categories);
        for (CategoryDTO ca: categories
             ) {
            System.out.println(ca.getName() + " đây này");
        }
        System.out.println(categories.size()+"sizfff");
        return "administrator/category-management";

    }
    @PostMapping("/getExcept")
    @ResponseBody
    public List<CategoryDTO> getExcept(@RequestBody ProductDTO productDTO){
        try{
            List<CategoryDTO> list = productDTO.getCategories();
            List<CategoryDTO> categoryDTOS = categoryService.findCategoriesByIdNotIn(list);
            return categoryDTOS;
        }
        catch (NoSuchElementException e){
            return null;
        }


    }
    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("categoryDto") CategoryDTO categoryDTO,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError -> model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));
            return "administrator/add-category";
        }
        categoryService.addCategory(categoryDTO);
        return "redirect:/admin/categories";

    }
    @GetMapping("/add")
    public String viewFormAdd(Model model){
        model.addAttribute("categoryDto" , new CategoryDTO());
        return "administrator/add-category";
    }
    @PostMapping("edit/{id}")
    public String editCategory(@PathVariable String id,Model model, @Valid @ModelAttribute("categoryDto") CategoryDTO categoryDTO, BindingResult bindingResult){
//      try{
//
//          Category category = categoryService.findById(id);
//          if(category.getParentCategory()==null){
//              category.setName(categoryDTO.getName());
//              categoryService.save(category);
//          }
//          else if(category.getParentCategory()!=null){
//              Category parent = categoryService.findById(categoryDTO.getParentId());
//              category.setParentCategory(parent);
//              Set<Category> childCategories = parent.getChildCategories();
//              childCategories.add(category);
//              categoryService.save(category);
//          }
//      }
//      catch(NoSuchElementException e){
//            return new ModelAndView("Không tìm thấy category");
//
//      }
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError -> model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        categoryService.updateCategory(categoryDTO,id);
        return "administrator/setting-category";
    }
    @Autowired
    CategoryRepository categoryRepository;
    @GetMapping("edit/{id}")
    public String viewFormEdit(@PathVariable String id,Model model){

        try {
            CategoryDTO categoryDTO = categoryService.findById(Integer.parseInt(id));
            System.out.println(categoryRepository.findEx().size()+"dhic");
            List<CategoryDTO> categoryDTOS = categoryService.findAllSubAndParCategories(37);
            System.out.println(categoryDTOS.size());
            System.out.println(categoryDTOS.size()+ "kai");
            model.addAttribute( "categories",categoryDTOS);
            model.addAttribute("categoryDto",categoryDTO);
        }
        catch (NoSuchElementException e){
            model.addAttribute("error",e.getMessage());
        }
        return "administrator/setting-category";

    }
}
