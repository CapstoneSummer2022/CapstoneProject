package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String id;
    private String name;
    private Integer unit;
    private String sku;
    private String image;
    private BigDecimal available;
    private BigDecimal price;
    private List<CategoryDTO> categories;
    private List<SpecificationValueDto> specificationValues;
    private int status ;
    private String description;
    private String supplierId;

}
