package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@CrossOrigin
@Controller
@RequestMapping("admin/products")
public class ProductController {
    final SupplierService supplierService;
    final
    ProductService productService;
    final CategoryService categoryService;
    final SpecificationService specificationService;
    final SkuService skuService;
    @GetMapping("")
    public String viewAll(Model model){
        List<ProductDTO> products =  productService.findAll();
        model.addAttribute("productDtos", products);
        return "administrator/product-management";
    }
    @GetMapping("/view/{id}")
    public String viewById(ModelMap model,@PathVariable @Valid String id){
        try{
            ProductDTO productDTO = productService.getProductDtoById(id);
            List<CategoryDTO> categoryDtos = categoryService.findCategoriesByIdNotIn(productDTO.getCategories());
            System.out.println("dcun");
            List<SpecificationValueDto> specificationValueDtos = productDTO.getSpecificationValues();
            List<Integer> sIds = new ArrayList<>();
            for (SpecificationValueDto c: specificationValueDtos
            ) {
                System.out.println(c.getSpecificationId()+"gia");
                sIds.add(Integer.parseInt(c.getSpecificationId()));
            }
            List<SpecificationDto> specificationDtos =specificationService.findSpecificationsBySpecificationIdNotIn(sIds);
            model.addAttribute("specificationDtos",specificationDtos);
            model.addAttribute("listSpecificationValue",specificationValueDtos);

            model.addAttribute("productDto",productDTO);
            model.addAttribute("listCategories",categoryDtos);

        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            model.addAttribute("notFound" ,e.getMessage());
        }
        return "administrator/setting-product";
    }
    @GetMapping("/getBySupplier")
    @ResponseBody
    public List<ProductDTO> getBySupplier(@RequestParam(name = "id") String id){

        SupplierDTO supplierDTO = supplierService.convertToDto(supplierService.getBySupplierId(id));
        return supplierDTO.getProducts();
    }
    @GetMapping("/add")
        public String viewProduct(ModelMap modelMap){
        List<CategoryDTO> listCategories = categoryService.findAll();
        List<SpecificationDto> specificationDtos = specificationService.findAll();
        List<SupplierDTO> supplierDTOS = supplierService.getAllSupplier();

        modelMap.addAttribute("listCategories",listCategories);
        modelMap.addAttribute("specificationDtos", specificationDtos);
        modelMap.addAttribute("listSuppliers",supplierDTOS);
            return "administrator/add-product";
        }

    @PostMapping("/add")
    @ResponseBody
    public String addNewProduct(@Valid @RequestBody ProductDTO productDTO){
        try{
            if(productService.addProduct(productDTO)){
                return "thành công";
            }
            else return "thất bại";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/getSku")
    @ResponseBody
    public List<SkuDTO> getSku(@RequestParam(name = "id") String id){
        try{
            System.out.println(id+"skusiz");
            System.out.println(skuService.getSkuDtoByProductId(id).size()+"skusize");
            return skuService.getSkuDtoByProductId(id);
        }catch (Exception e){
            System.out.println(e.getMessage()+"đây");
            return null;
        }

    }
    @PostMapping("/update/{id}")
    @ResponseBody
    public String update(@PathVariable Integer id,@Valid @RequestBody ProductDTO productDTO){


        return "administrator/product-management";
    }
    }


