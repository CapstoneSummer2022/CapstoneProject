package shop.services.impl;

import org.springframework.security.access.method.P;
import shop.config.ModelMap;
import shop.db.dto.category.CategoryDTO;
import shop.db.dto.product.ProductDTO;
import shop.db.dto.SpecificationValueDto;
import shop.db.dto.product.ListProductResponse;
import shop.db.entities.Category;
import shop.db.entities.ExportPrice;
import shop.db.entities.Product;
import shop.db.repositories.CategoryRepository;
import shop.db.repositories.ExportPriceRepository;
import shop.db.repositories.ProductRepository;
import shop.services.CategoryService;
import shop.services.ProductService;
import shop.services.SpecificationValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    ModelMap modelMap;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ExportPriceRepository exportPriceRepository;

    @Autowired
    SpecificationValueService specificationValueService;

    @Autowired
    CategoryRepository categoryRepo;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO convertToDto(Product product) {
        return modelMap.modelMapper().map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO getProductDtoById(String id) {
        return convertToDto(getById(id));
    }

    @Override
    public Product getById(String id) {
        try {
            Integer pId = Integer.parseInt(id);
            Optional<Product> productOptional = productRepository.findById(pId);
            if (productOptional.isEmpty()) {
                throw new NoSuchElementException("Không tìm thấy sản phẩm có mã như vậy");
            } else return productOptional.get();
        } catch (NumberFormatException e) {
            throw new NoSuchElementException("Không có sản phẩm này");
        }
    }

    public boolean updateProduct(ProductDTO productDTO, String id) {
        Product product = getById(id);
        product.setName(productDTO.getName());
        List<CategoryDTO> categoryDTOS = productDTO.getCategories();

        List<Category> categories = new ArrayList<>();
        for (CategoryDTO c : categoryDTOS
        ) {

            Category category = categoryService.getById(c.getId());
            categories.add(category);
        }
        product.setCategories(categories);
        Optional<ExportPrice> exportPriceOptional = exportPriceRepository.findByProductId(product.getId());
        if (exportPriceOptional.isPresent()) {
            ExportPrice exportPrice = exportPriceOptional.get();
            exportPriceRepository.save(exportPrice);
        } else {
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
        if (products.isEmpty()) {
            throw new NoSuchElementException("Không có sản phẩm nào");
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
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public ListProductResponse getProductByCategory(Pageable pageable) {
        List<Category> categories = categoryRepo.findAll();
        categories.forEach(p -> {

//            List<Product> products = productRepository.find
        });
        return null;
    }

}
