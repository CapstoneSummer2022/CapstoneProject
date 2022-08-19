package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.dto.OrderItemDto;
import com.example.electriccomponentsshop.entities.Order;
import com.example.electriccomponentsshop.entities.OrderItem;
import com.example.electriccomponentsshop.services.OrderItemService;
import com.example.electriccomponentsshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("admin/orders")
public class OrderController {
    private final OrderService orderService;

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
    @GetMapping("/view/{id}")
    public String viewOrder(@PathVariable String id, ModelMap modelMap){
        try {
            Integer orderId = Integer.parseInt(id);
            List<OrderItemDto> orderItemDtos = orderItemService.findByOrderId(orderId);
            modelMap.addAttribute("orderItemDtos",orderItemDtos);
            return "administrator/order-management";
        }catch (NumberFormatException | NoSuchElementException e){
            modelMap.addAttribute("notFound",e.getMessage());
            return  "administrator/order-management";
        }

    }
    @GetMapping("/view/{status}")
    public String viewOrderByStatus(@PathVariable String status, Model model){
        ArrayList<Order> orders = (ArrayList<Order>) orderService.findOrdersByStatus(status);
        model.addAttribute("orders", orders);
        return "administrator/order-detail";
    }
    @PostMapping("/update/id")
    public String updateStatus(@PathVariable String id, Model model){

        orderService.updateStatus(Integer.parseInt(id));
        return "";
    }
    @GetMapping("/add")
    public String viewAddForm(Model model){
        OrderDTO orderDTO = new OrderDTO();
        List<OrderItemDto> li = new ArrayList<>();
        li.add(new OrderItemDto("1","2-A","3","21200"));
        orderDTO.setOrderItemDtos(li);
        model.addAttribute("orderDto", new OrderDTO());
        return "administrator/create-new-order";
    }
    @PostMapping("/add")
    public String addOrder(@ModelAttribute("orderDto") OrderDTO orderDTO, Model model, BindingResult bindingResult){
        System.out.println("đay này");
        List<OrderItemDto> orderItemDtos = orderDTO.getOrderItemDtos();
        for (OrderItemDto o: orderItemDtos
             ) {
            System.out.println(o.getProductId());
            System.out.println(o.getQuantity());
        }
        return "";
    }
}
