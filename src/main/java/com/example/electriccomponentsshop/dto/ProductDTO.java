package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String name;
    @Column
    private String image;
    @Column
    private String description;
}
