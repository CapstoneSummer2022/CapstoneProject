package shop.services;

import shop.db.dto.product.ProductDTO;
import shop.db.dto.product.ListProductResponse;
import shop.db.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO convertToDto(Product product);

    ProductDTO getProductDtoById(String id);

    Product getById(String id);

    List<ProductDTO> findAll();

    <S extends Product> S save(S entity);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Integer integer);

    ListProductResponse getProductByCategory(Pageable pageable);
}
