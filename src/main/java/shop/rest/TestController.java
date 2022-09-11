package shop.rest;

import shop.config.ModelMap;
import shop.db.dto.SupplierDTO;
import shop.db.entities.Supplier;
import shop.db.repositories.*;
<<<<<<< HEAD:src/main/java/shop/rest/TestController.java
import shop.services.order.OrderItemService;
=======
import shop.services.OrderItemService;
>>>>>>> b767a4d (Refactor Code):src/main/java/com/example/electriccomponentsshop/controller/TestController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/")
public class TestController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private ModelMap modelMap;
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    OrderItemService orderItemRepository;
    @Autowired
    OrderItemRepository itemRepository;

    @PostMapping("/suppliers/add")
    public ResponseEntity<?> addNewSupplier(@Validated @RequestBody SupplierDTO supplierDTO)throws  Exception{
        Supplier s = convertToEntity(supplierDTO);
        supplierRepository.save(s);
        return ResponseEntity.ok(s);

    }
    @GetMapping("/error23")
    public String error(){
        return "signin1";
    }
    @GetMapping("/error-401")
    public String error401(){

        return "sos";
    }
    private SupplierDTO convertToDto(Supplier s){
        SupplierDTO supplierDTO = modelMap.modelMapper().map(s,SupplierDTO.class);
        return supplierDTO;
    }
    private Supplier convertToEntity(SupplierDTO supplierDTO){
        Supplier s =  modelMap.modelMapper().map(supplierDTO,Supplier.class);
        return s;
    }


}

