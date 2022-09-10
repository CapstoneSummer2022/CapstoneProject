package shop.rest.admin;

import shop.db.dto.ImportTransactionDto;
import shop.db.dto.WarehouseDTO;
import shop.db.entities.Warehouse;
import shop.services.WarehouseService;
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
@RequestMapping("admin/warehouses")
public class WarehouseController {
    final
    WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public String viewAll(Model model) {
        ArrayList<Warehouse> warehouses = (ArrayList<Warehouse>) warehouseService.findAll();
        model.addAttribute("warehouses", warehouses);
        return "administrator/warehouse-management";

    }

    @PostMapping("/view/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("warehouse") WarehouseDTO warehouseDTO, Model model) {
        Optional<Warehouse> warehouse = warehouseService.findById(id);
        if (warehouse.isPresent()) {
            Warehouse warehouse1 = warehouse.get();
//           warehouse1.setAddress(warehouseDTO.getAddress());
//           warehouse1.setStatus(warehouseDTO.getStatus());
            warehouseService.save(warehouse1);
        } else {
            model.addAttribute("error", "Không tìm thấy kho này");
        }
        return "";
    }

    @GetMapping("/add")
    public String viewAddForm(Model model) {
        model.addAttribute("newWarehouse", new Warehouse());
        return "administrator/add-warehouse";
    }

    @PostMapping("/add")
    public String addWarehouse(Model model, @Valid @ModelAttribute("newWarehouse") WarehouseDTO warehouseDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        } else {
            Warehouse warehouse = new Warehouse();
            warehouseService.save(warehouse);
        }
        return "administrator/add-warehouse";
    }

    @GetMapping("/import/add")
    public String viewFormAddImp(ModelMap modelMap) {
        modelMap.addAttribute("importTransaction", new ImportTransactionDto());
        return "administrator/add-warehouse-import";
    }

    @PostMapping("/import/add")
    @ResponseBody
    public String addImportTransaction(@Valid @RequestBody ImportTransactionDto importTransactionDto) {
        return null;
    }

}
