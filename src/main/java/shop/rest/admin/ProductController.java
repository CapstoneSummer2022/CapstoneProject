package shop.rest.admin;

import shop.db.dto.category.CategoryDTO;
import shop.db.dto.product.ProductDTO;
import shop.db.dto.SpecificationDto;
import shop.db.dto.SpecificationValueDto;
import shop.db.entities.Product;
import shop.services.CategoryService;
import shop.services.ProductService;
import shop.services.SpecificationService;
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
    final
    ProductService productService;
    final CategoryService categoryService;
    final SpecificationService specificationService;

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
    @GetMapping("/add")
        public String viewProduct(ModelMap modelMap){
         modelMap.addAttribute("products",new ProductDTO() );
            return "administrator/add-product";
        }
    @PostMapping("/add")
    @ResponseBody
    public String addNewProduct(@Valid @RequestBody ProductDTO productDTO){
        return "";
    }
    @PostMapping("/update/{id}")
    @ResponseBody
    public String update(@PathVariable Integer id,@Valid @RequestBody ProductDTO productDTO){

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


