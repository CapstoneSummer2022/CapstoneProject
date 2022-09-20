package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.services.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public String viewAll(Model model,@RequestParam(name="index",defaultValue = "0") String index){
        Page<ProductDTO> products =  productService.findAll(PageRequest.of(Integer.parseInt(index),10));
        model.addAttribute("productDtos", products.getContent());
        model.addAttribute("total",products.getTotalPages());
        return "administrator/product-management";
    }
    @GetMapping("/specification/add")
    public String getAddSpecForm(ModelMap modelMap){
        List<SpecificationDto> specificationDtos = specificationService.findAll();
        modelMap.addAttribute("listSpec",specificationDtos);
        modelMap.addAttribute("newSpecification",new SpecificationDto());
        return "administrator/add-product-specification";
    }
    @PostMapping("/specification/add")
    public String addNewSpecification(@ModelAttribute(name = "newSpecification") SpecificationDto specificationDto, BindingResult bindingResult,ModelMap modelMap){
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError -> modelMap.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        else{
            if(specificationService.addNewSpecification(specificationDto)){
                modelMap.addAttribute("success",1);
            }else{
                modelMap.addAttribute("success" , 0);
                modelMap.addAttribute("message", "Thông số này đã tồn tại");
            }
        }
        List<SpecificationDto> specificationDtos = specificationService.findAll();
        modelMap.addAttribute("listSpec",specificationDtos);
            return "administrator/add-product-specification";
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
        SupplierDTO supplierDTO =supplierService.getDtoById(id);
        return   supplierDTO.getProducts();
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
    @PostMapping("/disable")
    @ResponseBody
    public String disable(@RequestParam(name="id") String id ){
        try {
            productService.disableProduct(id);
            return "Vô hiệu hóa thành công";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    @PostMapping("/enable")
    @ResponseBody
    public String enable(@RequestParam(name="id") String id ){
        try {
            productService.enableProduct(id);
            return "Kích hoạt thành công";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    @GetMapping ("search-import")
    @ResponseBody
    public List<ProductDTO> getProductImport(@RequestParam(name="text") String text,@RequestParam(name="sId",defaultValue = "0")String sId){
            return productService.findBySupplierIdAndNameContain(sId,text);
    }
    @GetMapping("search")
    public String searchProduct(@RequestParam(name="text", required = false) String text, @RequestParam(name="index",defaultValue = "0") String index, ModelMap modelMap){
        int pIndex = Integer.parseInt(index);

        Page<ProductDTO> productDTOS = productService.searchProduct(text,PageRequest.of(pIndex,10));

        modelMap.addAttribute("pageNo", 1);
        modelMap.addAttribute("productDtos", productDTOS.getContent());
        modelMap.addAttribute("total", productDTOS.getTotalPages());
        modelMap.addAttribute("text", text);

        return "administrator/product-management";
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


