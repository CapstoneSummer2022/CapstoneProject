package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("admin/products")
public class ProductController {
    final
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String viewAll(Model model){
        ArrayList<Product> products = (ArrayList<Product>) productService.findAll();
        model.addAttribute("products", products);
        return "administrator/product-management";
    }
    @GetMapping("/view/{id}")
    public String viewById(ModelMap model,@PathVariable @Valid Integer id){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
            model.addAttribute("product1",product.get());
        }
        else {
            model.addAttribute("error","Không tồn tại");
        }
        return "administrator/product-management";
    }
    @PostMapping("/view/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute("product") @Valid ProductDTO productDTO, BindingResult bindingResult){
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()){
           Product product1 = product.get();
           product1.setName(productDTO.getName());
           product1.setImage(productDTO.getImage());
           product1.setDescription(productDTO.getDescription());
           productService.save(product1);

        }
        else {

        }
        return "administrator/product-management";
    }
    }


