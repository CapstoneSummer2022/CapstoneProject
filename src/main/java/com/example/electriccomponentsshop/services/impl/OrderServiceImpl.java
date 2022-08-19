package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.entities.Order;
import com.example.electriccomponentsshop.repositories.OrderRepository;
import com.example.electriccomponentsshop.services.OrderService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
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
    final ModelMap modelMap;
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
