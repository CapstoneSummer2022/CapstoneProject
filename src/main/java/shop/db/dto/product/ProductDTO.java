package shop.db.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.db.dto.category.CategoryDTO;
import shop.db.dto.SpecificationValueDto;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String id;
    private String sku;
    private String name;
    private String image;
    private Integer available;
    private Double price;
    private List<CategoryDTO> categories;
    private List<SpecificationValueDto> specificationValues;
    private int status ;
    private String description;

}
