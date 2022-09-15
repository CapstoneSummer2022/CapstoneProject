package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.dto.SpecificationValueDto;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.entities.ExportPrice;
import com.example.electriccomponentsshop.entities.Product;
import com.example.electriccomponentsshop.repositories.ExportPriceRepository;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.services.CategoryService;
import com.example.electriccomponentsshop.services.ProductService;
import com.example.electriccomponentsshop.services.SpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    EntityManager em;
    
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ModelMap modelMap;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ExportPriceRepository exportPriceRepository;
    @Autowired
    SpecificationValueService specificationValueService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private String sql = "select p.* from product p join product_category pc on pc.product_id = p.id "
            + " join category c on c.id = pc.category_id where status = 1 and path like :path " +
            " order by p.added_date desc, p.id desc";

    @Override
    public int countByCate(String cate) {
        Category category = categoryService.getById(cate);

        Query query = em.createNativeQuery(sql, Product.class);
        query.setParameter("path", category.getPath() + "%");

        List<Product> products = query.getResultList();

        return products.size();
    }

    @Override
    public List<ProductDTO> getProductByCate(String cate, int pageNo, int pageSize) {
        Category category = categoryService.getById(cate);

        Query query = em.createNativeQuery(sql, Product.class);
        query.setParameter("path", category.getPath() + "%");
        query.setFirstResult((pageNo-1)*pageSize);
        query.setMaxResults(pageSize);
        
        List<Product> products = query.getResultList();
        
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public  ProductDTO convertToDto(Product product){
        return modelMap.modelMapper().map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO getProductDtoById(String id){
        return convertToDto(getById(id));
    }
    @Override
    public Product getById(String id){
        try{
            Integer pId = Integer.parseInt(id);
            Optional<Product> productOptional = productRepository.findById(pId);
            if(productOptional.isEmpty()){
                throw new NoSuchElementException("Không tìm thấy sản phẩm có mã như vậy");
            }
            else return productOptional.get();
        } catch (NumberFormatException e){
            throw  new NoSuchElementException("Không có sản phẩm này");
        }
    }
    public boolean updateProduct(ProductDTO productDTO, String id){
       Product product = getById(id);
       product.setName(productDTO.getName());
       List<CategoryDTO> categoryDTOS = productDTO.getCategories();

       List<Category> categories = new ArrayList<>();
        for (CategoryDTO c: categoryDTOS
             ) {

            Category category = categoryService.getById(c.getId());
            categories.add(category);
        }
        product.setCategories(categories);
        Optional<ExportPrice> exportPriceOptional = exportPriceRepository.findByProductId(product.getId());
        if(exportPriceOptional.isPresent()){
            ExportPrice exportPrice = exportPriceOptional.get();
            exportPriceRepository.save(exportPrice);
        }
        else {
            ExportPrice newExportPrice = new ExportPrice();
            newExportPrice.setProduct(product);
            newExportPrice.setRetailPrice(productDTO.getPrice());
            exportPriceRepository.save(newExportPrice);
        }
        List<SpecificationValueDto> specificationValueDtos = productDTO.getSpecificationValues();
      return true;
    }
    @Override
    public List<ProductDTO> findAll() {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            throw  new NoSuchElementException("Không có sản phẩm nào");
        }
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }

    @Override
    public List<Product> findAll(Sort sort) {
        return productRepository.findAll(sort);
    }

    @Override
    public List<Product> findAllById(Iterable<Integer> integers) {
        return productRepository.findAllById(integers);
    }

    @Override
    public <S extends Product> List<S> saveAll(Iterable<S> entities) {
        return productRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        productRepository.flush();
    }

    @Override
    public <S extends Product> S saveAndFlush(S entity) {
        return productRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
        return productRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Product> entities) {
        productRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Product> entities) {
        productRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {
        productRepository.deleteAllByIdInBatch(integers);
    }

    @Override
    public void deleteAllInBatch() {
        productRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Product getOne(Integer integer) {
        return productRepository.getOne(integer);
    }

    @Override
    @Deprecated
    public Product getById(Integer integer) {
        return productRepository.getById(integer);
    }

    @Override
    public Product getReferenceById(Integer integer) {
        return productRepository.getReferenceById(integer);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example) {
        return productRepository.findAll(example);
    }

    @Override
    public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
        return productRepository.findAll(example, sort);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return productRepository.existsById(integer);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        productRepository.deleteAllById(integers);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        productRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public <S extends Product> Optional<S> findOne(Example<S> example) {
        return productRepository.findOne(example);
    }

    @Override
    public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
        return productRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Product> long count(Example<S> example) {
        return productRepository.count(example);
    }

    @Override
    public <S extends Product> boolean exists(Example<S> example) {
        return productRepository.exists(example);
    }

    @Override
    public <S extends Product, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return productRepository.findBy(example, queryFunction);
    }
}
