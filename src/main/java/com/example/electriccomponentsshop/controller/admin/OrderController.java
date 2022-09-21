package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Order;
import com.example.electriccomponentsshop.repositories.OrderItemRepository;
import com.example.electriccomponentsshop.repositories.OrderRepository;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    WardService wardService;
    @Autowired
    ProductService productService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderKindService orderKindService;
    @Autowired
    SkuService skuService;
    @GetMapping("")
    public String viewAll(ModelMap modelMap) {
        ArrayList<OrderDTO> orders = (ArrayList<OrderDTO>) orderService.findAll();
        modelMap.addAttribute("listOrder", orders);
        return "administrator/order-management";
    }

    private ModelMap getAddress(ModelMap model) {

        List<ProvinceDTO> provinceDTOS = provinceService.findAll();
        System.out.println(provinceDTOS.get(0).getName() + "đâ ");
        model.addAttribute("listProvince", provinceDTOS);
        List<DistrictDTO> districtDTOS = districtService.findByProvinceName(provinceDTOS.get(0).getName());
        System.out.println(districtDTOS.get(0).getName() + "đâ ");
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
    public String updateStatus(@PathVariable String id) {
        System.out.println("cập nhật");
        orderService.updateStatus(Integer.parseInt(id));
        return "redirect:/admin/orders/view/" + id;
    }
    @PostMapping("cancel/{id}")
    public String cancelOrder(@PathVariable String id){
        orderService.cancelOrder(id);
        return "redirect:/admin/orders/view/" + id;
    }
    @PostMapping("returned/{id}")
    public String returnedOrder(@PathVariable String id){
        orderService.returnedOrder(id);
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
    @GetMapping("/view")
    @ResponseBody
    public Map<String,List<SkuDTO>> getItems(@RequestParam(name="id") String id){
        try{
            Map<String,List<SkuDTO>> map = new HashMap<>();
            for (OrderItemDTO orderItemDTO: orderService.getOrderDtoById(id).getOrderItems()
                 ) {
                ObjectMapper objectMapper = new ObjectMapper();
                String itemDto = objectMapper.writeValueAsString(orderItemDTO);
                List<SkuDTO> skuDTOS = skuService.getSkuDtoByProductId(orderItemDTO.getProductId());
                map.put(itemDto,skuDTOS);
            }

            //System.out.println(orderService.getOrderDtoById(id).getOrderItems().size()+"dex");
            return map ;
        }catch (Exception e){
            System.out.println(e.getMessage()+"hoangdex");
          return null;
        }

    }
    @GetMapping("/add")
    public String viewAddForm(ModelMap model) {
        try{
            List<ProductDTO> productDTOList = productService.findAll();
            List<OrderKindDTO> orderTypeList = orderKindService.findAll();
            model.addAttribute("listProduct", productDTOList);
            model.addAttribute("listType", orderTypeList);
            getAddress(model);
        }
        catch (NoSuchElementException e){
            System.out.println(e.getMessage()+"ff");
            model.addAttribute("notFound", e.getMessage());

        }

            return "administrator/create-new-order";


    }

    @PostMapping("/add")
    @ResponseBody
    public String create(@Valid @RequestBody OrderDTO orderDTO) {

        try {
            if(orderService.createOrder(orderDTO)){
                return "thành công";
            }
            else    return "thất bại";
        } catch (Exception e) {
            return "thất bại";
        }

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody Map<String, String> error(MethodArgumentNotValidException m) {
        System.out.println("gg");
        Map<String, String> map = new HashMap<>();
        m.getFieldErrors().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));

        return map;
    }

}
