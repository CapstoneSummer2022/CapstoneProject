package shop.rest.admin;

import shop.config.ModelMap;
import shop.db.dto.SupplierDTO;
import shop.db.entities.Supplier;
import shop.services.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("admin/suppliers")
public class SupplierController {
    final
    SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public String viewAll(Model model){
        ArrayList<Supplier> suppliers = (ArrayList<Supplier>)supplierService.findAll();
        model.addAttribute("listSupplier",suppliers);
        return "administrator/supplier-management";
    }
    @PostMapping("/add")
    public String addNew(@Valid @ModelAttribute("newSupplier") SupplierDTO supplierDTO, BindingResult bindingResult){
        supplierService.save(convertToEntity(supplierDTO)) ;
        return "administrator/supplier-management";
    }
    @PostMapping("/view/{id}")
    public String update(@PathVariable Integer id,@Valid @ModelAttribute("newSupplier")SupplierDTO supplierDTO, BindingResult bindingResult){
        Optional<Supplier> supplier = supplierService.findById(id);
        if(supplier.isPresent()){
            supplierService.save(convertToEntity(supplierDTO));
        }
        else {

        }
        return "administrator/setting-management";
    }
    Supplier convertToEntity(SupplierDTO supplierDTO){
        ModelMap map = new ModelMap();
        return map.modelMapper().map(supplierDTO,Supplier.class);

    }
}
