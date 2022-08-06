package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.entities.Order;
import com.example.electriccomponentsshop.services.OrderService;
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
@RequestMapping("admin/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/view")
    public String viewAll(ModelMap modelMap){
       ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) orderService.findAll();
       modelMap.addAttribute("listOrder",orders);
       return "order-management";
    }
    @GetMapping("/view/{id}")
    public String viewOrder(@PathVariable int id, ModelMap modelMap){
        Optional<OrderDTO> orderOptional = orderService.findById(id);
        if(orderOptional.isPresent()){
            OrderDTO order = orderOptional.get();
            modelMap.addAttribute("order",order);
        }
        else
            modelMap.addAttribute("notFound","Đơn hàng không tồn tại");
            return "order-management";

    }
    @PostMapping("/view/{id}")
    public String update(@PathVariable int id, ModelMap modelMap, @Valid @ModelAttribute("order") OrderDTO orderDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError -> modelMap.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        Optional<OrderDTO> orderDTOOptional = orderService.findById(id);
        orderDTOOptional.ifPresent(dto -> orderService.save(orderService.convertToEntity(dto)));
        return "order-management";
    }
    @GetMapping("/view/{status}")
    public String viewOrderByStatus(@PathVariable String status, Model model){
        ArrayList<Order> orders = (ArrayList<Order>) orderService.findOrdersByStatus(status);
        model.addAttribute("orders", orders);
        return "order-management";
    }
}
