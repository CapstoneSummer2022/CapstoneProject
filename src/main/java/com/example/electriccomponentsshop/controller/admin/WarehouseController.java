package com.example.electriccomponentsshop.controller.admin;



import com.example.electriccomponentsshop.common.OrderEnum;
import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Warehouse;
import com.example.electriccomponentsshop.services.*;
import lombok.AllArgsConstructor;
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
    final ExportTransactionService exportTransactionService;
    final SupplierService supplierService;
    final ContainerService containerService;
    final OrderService orderService;
    @GetMapping("")
    public String viewAll(Model model){
        ArrayList<Warehouse> warehouses =(ArrayList<Warehouse>) warehouseService.findAll();
        model.addAttribute("warehouses",warehouses);
        return "administrator/warehouse-management";

    }
    @PostMapping("/view/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("warehouse") WarehouseDTO warehouseDTO, Model model){
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
        List<SupplierDTO> listSupplier = supplierService.getAllSupplier();
        List<WarehouseDTO> listWarehouse = warehouseService.getAllWarehouse();
        List<ContainerDTO> listContainer = containerService.getAllContainer();
        System.out.println(listContainer.stream().findFirst().get().getShelf()+ "gw") ;
        modelMap.addAttribute("listSupplier",listSupplier);
        modelMap.addAttribute("listWarehouses",listWarehouse);
        modelMap.addAttribute("listContainer",listContainer);
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
    public String addImportTransaction(@Valid @RequestBody ImportTransactionDto importTransactionDto, ModelMap modelMap){
        try{
            importTransactionService.addImportTransaction(importTransactionDto);
        }catch (NoSuchElementException e){
            modelMap.addAttribute("error" , e.getMessage());
            return e.getMessage();
        }
        return "thêm thành công";
    }
    @GetMapping("/view/export")
    public String viewExports(ModelMap modelMap){
        modelMap.addAttribute("listOfExport", new ModelMap());
        return "administrator/warehouse-export-management";
    }
    @GetMapping("/export/add")
    public String viewAddExport(ModelMap modelMap){
        try{
            List<OrderDTO> orderDTOList = orderService.findByStatus(OrderEnum.CONFIRM.getName());
            modelMap.addAttribute("listOrders", orderDTOList);
        }
        catch (Exception e){
            modelMap.addAttribute("notFound", e.getMessage());
        }
        return "administrator/add-warehouse-export";
    }
    @PostMapping("/export/add")
    @ResponseBody
    public String addExportTransaction(ModelMap modelMap,@Valid @RequestBody ExportTransactionDto exportTransactionDto){
        try{
            exportTransactionService.addExportTransaction(exportTransactionDto);
            return "Thêm thành công";
        }catch (NoSuchElementException e){
           return e.getMessage();
        }
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody Map<String,String> error(MethodArgumentNotValidException m){
        System.out.println("gg");
        Map<String,String> map = new HashMap<>();
        m.getFieldErrors().forEach(e->map.put(e.getField(),e.getDefaultMessage()));

        return map;
    }

}
