package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {
    private String id;
    private String provinceName;
    private String districtName;
    private String wardName;
    private String detailLocation;
    private Integer status;

}
