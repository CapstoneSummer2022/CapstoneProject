package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.SupplierDTO;
import com.example.electriccomponentsshop.entities.Supplier;
import com.example.electriccomponentsshop.services.SupplierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
        List<SupplierDTO> suppliers = supplierService.getAllSupplier();
        model.addAttribute("listSupplier",suppliers);
        return "administrator/supplier-management";
    }
    @PostMapping("/add")
    public String addNew(@Valid @ModelAttribute("newSupplier") SupplierDTO supplierDTO, BindingResult bindingResult){
        supplierService.addSupplier(supplierDTO);
        return "redirect:/admin/suppliers";
    }
    @GetMapping("/add")
    public String viewAddForm(ModelMap modelMap){
        modelMap.addAttribute("newSupplier",new SupplierDTO());
        return "administrator/add-supplier";
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable(name="id") String id,@Valid @ModelAttribute("supplier")SupplierDTO supplierDTO, BindingResult bindingResult){
        supplierService.updateSupplier(supplierDTO,id);
        return "administrator/setting-supplier";
    }
    @GetMapping("/edit/{id}")
    public String viewFormUpdate(@PathVariable(name="id") String id,ModelMap modelMap){
        SupplierDTO supplierDTO = supplierService.getDtoById(id);
        modelMap.addAttribute("supplier",supplierDTO);
        return "administrator/setting-supplier";
    }
    @PostMapping("/disable")
    @ResponseBody
    public String disable(@RequestParam(name="id") String id ){
        try {
            supplierService.disableSupplier(id);
            return "Vô hiệu hóa thành công";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    @PostMapping("/enable")
    @ResponseBody
    public String enable(@RequestParam(name="id") String id ){
        try {
            supplierService.enableSupplier(id);
            return "Kích hoạt thành công";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }

}
