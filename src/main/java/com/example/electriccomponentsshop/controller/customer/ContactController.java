package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.entities.StoreInformation;
import com.example.electriccomponentsshop.repositories.StoreRepository;
import com.example.electriccomponentsshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/contact")
    public String getContact (ModelMap map){
        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();

        map.addAttribute("categories", categories);
        map.addAttribute("storesInfo", storeRepository.findAll());

        return "customer/contact";
    }

    @GetMapping(value = "/store-info-api")
    @ResponseBody
    public List<StoreInformation> getStoreInfoApi () {
        return storeRepository.findAll();
    }

}
