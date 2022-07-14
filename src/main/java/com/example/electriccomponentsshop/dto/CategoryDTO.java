package com.example.electriccomponentsshop.dto;

import com.example.electriccomponentsshop.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer parentId;
    private String name;

}
