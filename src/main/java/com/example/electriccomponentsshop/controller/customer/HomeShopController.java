package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.services.CategoryService;
import com.example.electriccomponentsshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/home")
public class HomeShopController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @GetMapping(value = "")
    public String getInfoForHome (ModelMap map) {
        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();
        Map<CategoryDTO, List<ProductDTO>> productsByCate = new HashMap<>();

        for (Category cateTmp : categories) {
            List<ProductDTO> productDTOS = productService.getProductByCate(String.valueOf(cateTmp.getId()),1, 6);

            if (!productDTOS.isEmpty()) {
                CategoryDTO cateDto = categoryService.convertToDto(cateTmp);
                productsByCate.put(cateDto, productDTOS);
            }
        }

        map.addAttribute("categories", categories);
        map.addAttribute("productsByCate", productsByCate);

        return "customer/home";
    }
}
