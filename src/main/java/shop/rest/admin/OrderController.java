package shop.rest.admin;

<<<<<<< HEAD:src/main/java/shop/rest/admin/OrderController.java
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import shop.db.dto.ResponseObject;
import shop.db.dto.order.OrderDTO;
=======
import shop.db.dto.OrderDTO;
>>>>>>> b767a4d (Refactor Code):src/main/java/com/example/electriccomponentsshop/controller/admin/OrderController.java
import shop.db.dto.ProvinceDTO;
import shop.db.entities.Order;
import shop.db.repositories.OrderItemRepository;
import shop.db.repositories.OrderRepository;
import shop.db.repositories.ProductRepository;
import shop.db.dto.DistrictDTO;
import shop.db.dto.WardDTO;
<<<<<<< HEAD:src/main/java/shop/rest/admin/OrderController.java
import shop.rest.endpoint.Endpoints;
=======
>>>>>>> b767a4d (Refactor Code):src/main/java/com/example/electriccomponentsshop/controller/admin/OrderController.java
import shop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import shop.services.order.OrderItemService;
import shop.services.order.OrderService;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("admin/orders")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    OrderRepository r;
    @Autowired
    ProductRepository p;

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;
    @Autowired
    WardService wardService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("")
    public String viewAll(ModelMap modelMap) {
        ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) orderService.findAll();
        modelMap.addAttribute("listOrder", orders);

        return "administrator/order-management";
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

    @GetMapping("/view/{id}")

    public String viewOrder(@PathVariable String id, Model modelMap) {
        try {
            Integer orderId = Integer.parseInt(id);

            OrderDTO orderDTO = orderService.findById(orderId);
            modelMap.addAttribute("orderDto", orderDTO);
            return "administrator/order-detail";
        } catch (NumberFormatException | NoSuchElementException e) {
            modelMap.addAttribute("notFound", e.getMessage());
            return "administrator/order-detail";
        }

    }

    @GetMapping("/see/{status}")
    public String viewOrderByStatus(@PathVariable String status, ModelMap model) {
        ArrayList<Order> orders = (ArrayList<Order>) orderService.findOrdersByStatus(status);
        model.addAttribute("orders", orders);
        return "administrator/order-management";
    }

    @GetMapping("/update/{id}")
    public String viewOrderUpdate(@PathVariable String id, ModelMap modelMap) {
        System.out.println("capp");
        OrderDTO orderDTO = orderService.findById(Integer.parseInt(id));
        modelMap.addAttribute("orderDto", orderDTO);
        getAddress(modelMap);
        return "administrator/setting-order";
    }

    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable String id, ModelMap model) {
        System.out.println("cập nhật");
        orderService.updateStatus(Integer.parseInt(id));
        return "redirect:/admin/orders/view/" + id;
    }

    @PostMapping("/save/{id}")
    @ResponseBody
    public String saveOrder(@Valid @RequestBody OrderDTO orderDTO, @PathVariable String id, ModelMap model) {
        String response = "";
        System.out.println("đây cơ");
        if (orderService.updateOrder(id, orderDTO)) {
            response = "thành công";
            System.out.println(response);
        } else response = "thất bại";
        return response;
    }

    @GetMapping("/add")
    public String viewAddForm(ModelMap model) {
//        OrderDTO orderDTO = new OrderDTO();
//        List<OrderItemDto> li = new ArrayList<>();
//        li.add(new OrderItemDto("1","2-A","3","21200"));
//        orderDTO.setOrderItemDtos(li);
//        model.addAttribute("orderDto", orderDTO);
        model.addAttribute("orderDto", new OrderDTO());
        getAddress(model);
        return "administrator/create-new-order";
    }

    @PostMapping(Endpoints.ORDER_URL)
    public final ResponseEntity<Object> create(@Valid @RequestBody OrderDTO orderDTO) {
        ResponseObject<Boolean> response = new ResponseObject<>();
        try {
            response.setResponseData(orderService.createOrder(orderDTO));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody Map<String, String> error(MethodArgumentNotValidException m) {
        System.out.println("gg");
        Map<String, String> map = new HashMap<>();
        m.getFieldErrors().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));

        return map;
    }

}
