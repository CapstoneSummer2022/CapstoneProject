package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.dto.SpecificationValueDto;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.services.CategoryService;
import com.example.electriccomponentsshop.services.ProductService;
import com.example.electriccomponentsshop.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/product")
public class ProductShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/{id}")
    //@ResponseBody
    public String getProduct (ModelMap map, @PathVariable String id) {
        ProductDTO productDTO = null;

        try {
            productDTO = productService.getProductDtoById(id);
            List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();

            map.addAttribute("categories", categories);
            map.addAttribute("product", productDTO);
        } catch (NoSuchElementException e) {
            throw e;
        }

        return "customer/product";
    }

    @GetMapping(value = "")
    public String getProductByCateInFirstPage (@RequestParam String cate,
                                               @RequestParam String pageNo, ModelMap model) {
        Category category = null;
        List<ProductDTO> products = null;

        try {
            int no = Integer.parseInt(pageNo);

            int pageNum = (int) Math.ceil(productService.countByCate(cate)/18.0);
            List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();

            category = categoryService.getById(cate);
            products = productService.getProductByCate(cate, no, 18);

            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
            model.addAttribute("category", category);
            model.addAttribute("pageNum", pageNum);
            model.addAttribute("active", no);
        } catch (Exception e) {
            throw e;
        }

        return "customer/product-by-cgr";
    }
}
