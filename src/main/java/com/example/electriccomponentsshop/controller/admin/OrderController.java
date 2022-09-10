package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Order;
import com.example.electriccomponentsshop.entities.OrderItem;
import com.example.electriccomponentsshop.entities.OrderItemId;
import com.example.electriccomponentsshop.repositories.OrderItemRepository;
import com.example.electriccomponentsshop.repositories.OrderRepository;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
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
    WardService wardService ;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    OrderItemService orderItemService;
    @GetMapping("")
    public String viewAll(ModelMap modelMap){
       ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) orderService.findAll();
       modelMap.addAttribute("listOrder",orders);

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

    public String viewOrder(@PathVariable String id, Model modelMap){
        try {
            Integer orderId = Integer.parseInt(id);

            OrderDTO orderDTO = orderService.findById(orderId);
            modelMap.addAttribute("orderDto",orderDTO);
            return "administrator/order-detail";
        }catch (NumberFormatException | NoSuchElementException e){
            modelMap.addAttribute("notFound",e.getMessage());
            return  "administrator/order-detail";
        }

    }
    @GetMapping("/see/{status}")
    public String viewOrderByStatus(@PathVariable String status, ModelMap model){
        ArrayList<Order> orders = (ArrayList<Order>) orderService.findOrdersByStatus(status);
        model.addAttribute("orders", orders);
        return "administrator/order-management";
    }
    @GetMapping("/update/{id}")
    public String viewOrderUpdate(@PathVariable String id, ModelMap modelMap){
        System.out.println("capp");
        OrderDTO  orderDTO= orderService.findById(Integer.parseInt(id));
        modelMap.addAttribute("orderDto", orderDTO);
        getAddress(modelMap);
        return "administrator/setting-order";
    }
    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable String id, ModelMap model){
        System.out.println("cập nhật");
        orderService.updateStatus(Integer.parseInt(id));
        return "redirect:/admin/orders/view/" + id;
    }
    @PostMapping("/save/{id}")
    @ResponseBody
    public String saveOrder(@Valid @RequestBody OrderDTO orderDTO,@PathVariable String id, ModelMap model){
        String response = "";
        System.out.println("đây cơ");
        if(orderService.updateOrder(id,orderDTO)){
            response ="thành công";
            System.out.println(response);
        }else response="thất bại";
            return response;
    }
    @GetMapping("/add")
    public String viewAddForm(ModelMap model){
//        OrderDTO orderDTO = new OrderDTO();
//        List<OrderItemDto> li = new ArrayList<>();
//        li.add(new OrderItemDto("1","2-A","3","21200"));
//        orderDTO.setOrderItemDtos(li);
//        model.addAttribute("orderDto", orderDTO);
        model.addAttribute("orderDto", new OrderDTO());
        getAddress(model);
        return "administrator/create-new-order";
    }
    @PostMapping("/add")
    @ResponseBody
    public  String addOrder(@Valid @RequestBody OrderDTO orderDTO, ModelMap model) {
      String response="";
        System.out.println("lôi đau");
        if (orderService.addOrder(orderDTO)) {
            response = "thành công";
        }
        else {
            response ="thất bại";
        }
       return "thất bại";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody Map<String,String> error(MethodArgumentNotValidException m){
        System.out.println("gg");
        Map<String,String> map = new HashMap<>();
        m.getFieldErrors().forEach(e->map.put(e.getField(),e.getDefaultMessage()));

        return map;
    }

}
