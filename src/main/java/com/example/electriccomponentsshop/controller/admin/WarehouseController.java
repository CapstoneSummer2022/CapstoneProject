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
    final ProvinceService provinceService;
    final DistrictService districtService;
    final WardService wardService;
    @GetMapping("")
    public String viewAll(Model model){
        List<WarehouseDTO> warehouses =warehouseService.getAllWarehouse();
        model.addAttribute("warehouses",warehouses);
        return "administrator/warehouse-management";

    }
    private ModelMap getAddress(ModelMap model) {

        List<ProvinceDTO> provinceDTOS = provinceService.findAll();
        model.addAttribute("listProvince", provinceDTOS);
        List<DistrictDTO> districtDTOS = districtService.findByProvinceName(provinceDTOS.get(0).getName());
        model.addAttribute("listDistrict", districtDTOS);
        List<WardDTO> wardDTOS = wardService.findByDistrictName(districtDTOS.get(0).getName());
        model.addAttribute("listWard", wardDTOS);
        return model;
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable(name="id") String id, @Valid @ModelAttribute("warehouseDto") WarehouseDTO warehouseDTO, ModelMap modelMap,BindingResult bindingResult){
        try{
            if(bindingResult.hasErrors()){
                bindingResult.getFieldErrors().forEach(fieldError -> modelMap.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));

            }else {
                warehouseService.update(warehouseDTO,id);
                modelMap.addAttribute("success",1);
            }

        }catch (RuntimeException e){
            modelMap.addAttribute("error",e.getMessage());
        }
       return "redirect:/admin/warehouses";
    }
    @GetMapping("/edit/{id}")
    public String viewUpdateForm(ModelMap modelMap,@PathVariable(name="id") String id){
        try{
            getAddress(modelMap);
            WarehouseDTO warehouseDTO = warehouseService.getWarehouseDtoById(id);
            modelMap.addAttribute("warehouseDto",warehouseDTO);
        }catch (RuntimeException e){
            modelMap.addAttribute("error", e.getMessage());
        }
        return "administrator/setting-warehouse";
    }
    @GetMapping("/add")
    public String viewAddForm(ModelMap model){
        getAddress(model);
        model.addAttribute("newWarehouse",new WarehouseDTO());
        return "administrator/add-warehouse";
    }
    @PostMapping("/add")
    public String addWarehouse(Model model, @Valid @ModelAttribute("newWarehouse") WarehouseDTO warehouseDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){

        }
        else {
            warehouseService.add(warehouseDTO);
        }
        return "redirect:/admin/warehouses";
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
            return "thành công";
        }catch (NoSuchElementException e){
            return e.getMessage();
        }

    }
    @GetMapping("/import/update/{id}")
    public String updateImportTransaction(ModelMap modelMap, @PathVariable(name = "id") String id){
        try{
            ImportTransactionDto importTransactionDto = importTransactionService.getDtoById(id);
            modelMap.addAttribute("importTransactionDto",importTransactionDto);

        }catch (NoSuchElementException e){
            modelMap.addAttribute("notFound","Không tìm thấy giao dịch này");
            modelMap.addAttribute("success", 0);
        }
        return "administrator/setting-warehouse-import";
    }
    @PostMapping("/disable")
    @ResponseBody
    public String disable(@RequestParam(name="id") String id ){
        try {
            warehouseService.disableWarehouse(id);
            return "Vô hiệu hóa thành công";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    @PostMapping("/enable")
    @ResponseBody
    public String enable(@RequestParam(name="id") String id ){
        try {
            warehouseService.enableWarehouse(id);
            return "Kích hoạt thành công";
        }catch (RuntimeException e){
            return e.getMessage();
        }
    }
    @GetMapping("/view/export")
    public String viewExports(ModelMap modelMap){
        try{
            List<ExportTransactionDto> exportTransactionDtos =exportTransactionService.findAll();
            modelMap.addAttribute("listOfExport", exportTransactionDtos );
        }catch (NoSuchElementException e){
            modelMap.addAttribute("notFound","Không có dữ liệu");
        }
        return "administrator/warehouse-export-management";
    }
    @PostMapping("/export/update")
    @ResponseBody
    public String updateExport(@Valid @RequestBody ExportTransactionDto exportTransactionDto){
        String response = "";
        try{
            exportTransactionService.updateExportTransaction(exportTransactionDto);
            response ="cập nhật thành công";

        }catch (RuntimeException e){
            response = e.getMessage();
        }
        return  response;
    }
    @GetMapping("/export/edit/{id}")
    public String viewFormUpdateExport(ModelMap modelMap,@PathVariable(name = "id") String id){
        ExportTransactionDto exportTransactionDto = exportTransactionService.getDtoById(id);
        modelMap.addAttribute("exportTransaction" , exportTransactionDto);
        return "administrator/setting-warehouse-export";
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
            return "thành công";
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
