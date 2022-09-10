package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecificationValueDto {
    private String productId;
    private String specificationId;
    private String specificationName;
    private String valueFrom;
    private String valueTo;
}
