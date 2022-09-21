package com.example.electriccomponentsshop.services.impl;

import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.dto.ProductDTO;
import com.example.electriccomponentsshop.dto.SpecificationValueDto;
import com.example.electriccomponentsshop.entities.*;
import com.example.electriccomponentsshop.repositories.ExportPriceRepository;
import com.example.electriccomponentsshop.repositories.ProductRepository;
import com.example.electriccomponentsshop.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SpecificationService specificationService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    EntityManager em;

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
    public ProductDTO convertToDto(Product product) {
        return modelMap.modelMapper().map(product, ProductDTO.class);
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
        List<SpecificationValue> specificationValues = new ArrayList<>();
        for (SpecificationValueDto s: specificationValueDtos
             ) {

            Specification specification = specificationService.getById(s.getSpecificationId());
            SpecificationValue specificationValue = new SpecificationValue(new SpecificationValueId(product.getId(),specification.getId()),s.getValueFrom(),s.getValueTo(),product,specification);
            specificationValues.add(specificationValue);
            specificationValueService.save(specificationValue);
        }
        product.setStatus(1);
        product.setSpecificationValues(specificationValues);
        product.setDescription(productDTO.getDescription());
      return  productRepository.save(product)!=null;

    }
    @Override
    public boolean addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setImage("dd");
        product.setName(productDTO.getName());
        product.setUnit(productDTO.getUnit());
        product.setAvailable(new BigInteger("0"));
        List<CategoryDTO> categoryDTOS = productDTO.getCategories();
        List<Category> categories = new ArrayList<>();
        for (CategoryDTO c : categoryDTOS
        ) {
            Category category = categoryService.getById(c.getId());
            categories.add(category);
        }
        Supplier supplier = supplierService.getBySupplierId(productDTO.getSupplierId());
        product.setProductSupplier(supplier);
        product.setCategories(categories);
        product = productRepository.save(product);
            ExportPrice newExportPrice = new ExportPrice();
            newExportPrice.setProduct(product);
            newExportPrice.setRetailPrice(productDTO.getPrice());
            exportPriceRepository.save(newExportPrice);

        List<SpecificationValueDto> specificationValueDtos = productDTO.getSpecificationValues();
        List<SpecificationValue> specificationValues = new ArrayList<>();
        for (SpecificationValueDto s: specificationValueDtos
        ) {
            Specification specification = specificationService.getById(s.getSpecificationId());
            SpecificationValue specificationValue = new SpecificationValue(new SpecificationValueId(product.getId(),specification.getId()),s.getValueFrom(),s.getValueTo(),product,specification);
            specificationValues.add(specificationValue);
            specificationValueService.save(specificationValue);
        }
        product.setStatus(1);
        product.setSpecificationValues(specificationValues);
        product.setDescription(productDTO.getDescription());
        return  productRepository.save(product)!=null;

    }
    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    @Override
    public void disableProduct(String id){
        Product product = getById(id);
        product.setStatus(0);
        productRepository.save(product);
    }
    @Override
    public void enableProduct(String id){
        Product product = getById(id);
        product.setStatus(1);
        productRepository.save(product);
    }
    @Override
    public Page<ProductDTO> searchProduct(String text, Pageable pageable){
        return productRepository.searchProductsByNameContain(text,pageable).map(this::convertToDto);
    }
    @Override
    public Page<ProductDTO> findBySupplierId(Pageable pageable, String sId){
        Supplier supplier = supplierService.getBySupplierId(sId);
        return productRepository.findProductsByProductSupplierId(supplier.getId(),pageable).map(this::convertToDto);
    }
    @Override
    public List<ProductDTO> findBySupplierIdAndNameContain(String sId, String text){
        Supplier supplier = supplierService.getBySupplierId(sId);
        return productRepository.findProductsByProductSupplierIdAndNameContains(supplier.getId(),text).stream().map(this::convertToDto).collect(Collectors.toList());
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
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> productPages = productRepository.findAll(pageable);
        Page<ProductDTO> dtoPage = productPages.map(this::convertToDto);
        return dtoPage;
    }

    @Override
    public <S extends Product> S save(S entity) {
        return productRepository.save(entity);
    }


}
