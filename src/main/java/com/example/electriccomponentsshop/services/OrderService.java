package com.example.electriccomponentsshop.services;

import com.example.electriccomponentsshop.dto.OrderDTO;
import com.example.electriccomponentsshop.entities.Order;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface OrderService {
    List<OrderDTO> findAll();
    List<Order> findOrdersByStatus(String status);
    List<Order> findAll(Sort sort);
    Order convertToEntity(OrderDTO orderDTO);
    OrderDTO convertToDTO(Order order);
    List<Order> findAllById(Iterable<Integer> integers);
    boolean addOrder(OrderDTO orderDTO);
    boolean updateOrder(String id, OrderDTO orderDTO);
    <S extends Order> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Order> S saveAndFlush(S entity);

    <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Order> entities);

    void deleteAllInBatch(Iterable<Order> entities);

    void deleteAllByIdInBatch(Iterable<Integer> integers);

    void deleteAllInBatch();



    boolean updateStatus(Integer id);
    @Deprecated
    Order getOne(Integer integer);

    @Deprecated
    Order getById(Integer integer);

    Order getReferenceById(Integer integer);

    <S extends Order> List<S> findAll(Example<S> example);

    <S extends Order> List<S> findAll(Example<S> example, Sort sort);

    Page<Order> findAll(Pageable pageable);

    <S extends Order> S save(S entity);

    OrderDTO findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Order entity);

    void deleteAllById(Iterable<? extends Integer> integers);

    void deleteAll(Iterable<? extends Order> entities);

    void deleteAll();

    <S extends Order> Optional<S> findOne(Example<S> example);

    <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Order> long count(Example<S> example);

    <S extends Order> boolean exists(Example<S> example);

    <S extends Order, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
