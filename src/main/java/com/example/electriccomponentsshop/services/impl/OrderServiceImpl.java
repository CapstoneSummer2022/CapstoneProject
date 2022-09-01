package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.dto.OrderItemDto;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.*;
import com.example.electriccomponentsshop.services.AccountDetailImpl;
import com.example.electriccomponentsshop.services.OrderService;
import com.example.electriccomponentsshop.services.ProvinceService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;
    final AccountRepository accountRepository;
    final ModelMap modelMap;
    final ProductRepository productRepository;
    final ProvinceRepository provinceRepository;
    final DistrictRepository districtRepository;
    final WardRepository wardRepository;
    @Override
    public List<Order> findOrdersByStatus(String status) {
        return orderRepository.findOrdersByStatus(status);
    }
    public Order convertToEntity(OrderDTO orderDTO){
        return modelMap.modelMapper().map(orderDTO,Order.class);
    }
    public OrderDTO convertToDTO(Order order){
        return modelMap.modelMapper().map(order,OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order o: orderList
             ) {
            orderDTOList.add(convertToDTO(o));
        }
        return orderDTOList;
    }
    private boolean setAddress(OrderDTO orderDTO, Order order) {
        Optional<Province> provinceOptional = provinceRepository.findByName(orderDTO.getProvinceName());

        if(provinceOptional.isEmpty()){
            throw new NoSuchElementException("Không tìm thấy tỉnh/thành phố này");
        }
        else {
            Province province = provinceOptional.get();
            List<District> districtList = province.getDistrictList();
            Optional<District> districtOptional = districtRepository.findByName(orderDTO.getDistrictName());
            if(districtOptional.isEmpty()){
                throw new NoSuchElementException("Không tìm thấy Quận/Huyện này");
            }
            else {
                District district = districtOptional.get();
                if(!districtList.contains(district)){
                    throw new NoSuchElementException("Tỉnh/Thành phố này không có Quận/Huyện này");
                }
                else {
                    Optional<Ward> wardOptional = wardRepository.findByName(orderDTO.getWardName());
                    List<Ward> wardList = district.getWardList();
                    if(wardOptional.isEmpty()){
                        throw new NoSuchElementException("Không có Xã/Phường/Thị trấn này");
                    }
                    else {
                        Ward ward = wardOptional.get();
                        if(!wardList.contains(ward)){
                            throw new NoSuchElementException("Quận/Huyện này không có Xã/Phường/Thị trấn này");
                        }
                        else {
                            order.setProvinceOrder(province);
                            order.setDistrictOrder(district);
                            order.setWardOrder(ward);
                        }

                    }
                }

            }
        }
        return true;
    }
    @Override
    public boolean addOrder(OrderDTO orderDTO){
        Order order = new Order();
        order.setStatus("Chờ xử lý");
        List<OrderItemDto> dtos= orderDTO.getOrderItemDtos();
        Double totalPayment = 0.0;
        setAddress(orderDTO,order);
        order.setDetailLocation("");
        order.setReceivedPerson(orderDTO.getReceivedPerson());
        order.setReceivedPhone(orderDTO.getReceivedPhone());
        for (OrderItemDto o : dtos
             ) {
            Integer quantity = Integer.parseInt(o.getQuantity());
            Product p = productRepository.findById(Integer.parseInt(o.getProductId())).get();
            totalPayment+= quantity*p.getExportPrice().getRetailPrice();
        }
        order.setTotalPayment(totalPayment);
        setAddress(orderDTO,order);
        List<Account> li = new ArrayList<>();
        AccountDetailImpl accountDetail = (AccountDetailImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Account> accountOptional = accountRepository.findByEmail(accountDetail.getEmail());
        if(accountOptional.isEmpty()){
            throw  new NoSuchElementException("Không tìm thấy nhân viên này");
        }
        else li.add(accountOptional.get());
        order.setAccounts(li);
        return orderRepository.save(order)!=null;

    }
    public boolean updateStatus(Integer id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        if(orderOptional.isPresent()){
            Order order = orderOptional.get();
            String status = order.getStatus();
            if(status.equalsIgnoreCase("Chờ xử lý")){
                order.setStatus("Đang giao hàng");
            }
            else if(status.equals("Đang giao hàng")){
                order.setStatus("Hoàn thành");
            }
            else{
                throw new RuntimeException("Thao tác không được thực hiện");
            }
            orderRepository.save(order);
            return true;
        }
        else {
            throw  new NoSuchElementException("");
        }
    }


    @Override
    public List<Order> findAll(Sort sort) {
        return orderRepository.findAll(sort);
    }

    @Override
    public List<Order> findAllById(Iterable<Integer> integers) {
        return orderRepository.findAllById(integers);
    }

    @Override
    public <S extends Order> List<S> saveAll(Iterable<S> entities) {
        return orderRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        orderRepository.flush();
    }

    @Override
    public <S extends Order> S saveAndFlush(S entity) {
        return orderRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
        return orderRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Order> entities) {
        orderRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Order> entities) {
        orderRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        orderRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        orderRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Order getOne(Integer integer) {
        return orderRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public Order getById(Integer integer) {
        return orderRepository.getById(integer);
    }

    @Override
    public Order getReferenceById(Integer integer) {
        return orderRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example) {
        return orderRepository.findAll(example);
    }

    @Override
    public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
        return orderRepository.findAll(example, sort);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }

    @Override
    public OrderDTO findById(Integer integer) {
        Optional<Order> orderOptional = orderRepository.findById(integer);
        if(orderOptional.isEmpty()){
            throw  new NoSuchElementException("Không tìm thấy đơn hàng với id này");
        }
         return convertToDTO(orderOptional.get());
    }

    @Override
    public boolean existsById(Integer integer) {
        return orderRepository.existsById(integer);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        orderRepository.deleteById(integer);
    }

    @Override
    public void delete(Order entity) {
        orderRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        orderRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {
        orderRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        orderRepository.deleteAll();
    }

    @Override
    public <S extends Order> Optional<S> findOne(Example<S> example) {
        return orderRepository.findOne(example);
    }

    @Override
    public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
        return orderRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Order> long count(Example<S> example) {
        return orderRepository.count(example);
    }

    @Override
    public <S extends Order> boolean exists(Example<S> example) {
        return orderRepository.exists(example);
    }

    @Override
    public <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return orderRepository.findBy(example, queryFunction);
    }
}
