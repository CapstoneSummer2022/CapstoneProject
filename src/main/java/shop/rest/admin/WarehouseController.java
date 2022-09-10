package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.ImportTransactionDto;
import com.example.electriccomponentsshop.dto.WarehouseDTO;
import com.example.electriccomponentsshop.entities.Warehouse;
import com.example.electriccomponentsshop.services.ImportTransactionService;
import com.example.electriccomponentsshop.services.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin
@Controller
@AllArgsConstructor
@RequestMapping("admin/warehouses")
public class WarehouseController {
    final
    WarehouseService warehouseService;
    final
    ImportTransactionService importTransactionService;
    @GetMapping("")
    public String viewAll(Model model){
        ArrayList<Warehouse> warehouses =(ArrayList<Warehouse>) warehouseService.findAll();
        model.addAttribute("warehouses",warehouses);
        return "administrator/warehouse-management";

    }
    @PostMapping("/view/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("warehouse")WarehouseDTO warehouseDTO,Model model){
       Optional<Warehouse>  warehouse = warehouseService.findById(id);
       if(warehouse.isPresent()){
           Warehouse warehouse1 = warehouse.get();
//           warehouse1.setAddress(warehouseDTO.getAddress());
//           warehouse1.setStatus(warehouseDTO.getStatus());
           warehouseService.save(warehouse1);
       }
       else {
           model.addAttribute("error","Không tìm thấy kho này");
       }
       return "";
    }
    @GetMapping("/add")
    public String viewAddForm(Model model){
        model.addAttribute("newWarehouse",new Warehouse());
        return "administrator/add-warehouse";
    }
    @PostMapping("/add")
    public String addWarehouse(Model model, @Valid @ModelAttribute("newWarehouse") WarehouseDTO warehouseDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

        }
        else {
            Warehouse warehouse = new Warehouse();
            warehouseService.save(warehouse);
        }
        return "administrator/add-warehouse";
    }
    @GetMapping("/import/add")
    public String viewFormAddImp(ModelMap modelMap){
        modelMap.addAttribute("importTransaction", new ImportTransactionDto());
        return "administrator/add-warehouse-import";
    }
    @GetMapping("/view/import")
    public String viewListImport(ModelMap modelMap){
        try{
            List<ImportTransactionDto> importTransactionDtoList =importTransactionService.findAll();
            modelMap.addAttribute("listImport", importTransactionDtoList );
        }catch (NoSuchElementException e){
            modelMap.addAttribute("notFound","Không có dữ liệu");
        }
        return "administrator/warehouse-import-management";
    }
    @PostMapping("/import/add")
    @ResponseBody
    public String addImportTransaction(@Valid @RequestBody ImportTransactionDto importTransactionDto,ModelMap modelMap){
        try{
            importTransactionService.addImportTransaction(importTransactionDto);
        }catch (NoSuchElementException e){
            modelMap.addAttribute("error" , e.getMessage());
            return "administrator/add-warehouse-import";
        }
        return "redirect:/admin/warehouses/view/import";
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody Map<String,String> error(MethodArgumentNotValidException m){
        System.out.println("gg");
        Map<String,String> map = new HashMap<>();
        m.getFieldErrors().forEach(e->map.put(e.getField(),e.getDefaultMessage()));

        return map;
    }

}
