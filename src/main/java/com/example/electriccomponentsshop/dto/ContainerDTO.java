package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ContainerDTO {

    private int id;
    private int rowIn;
    private int columnIn;
    private String shelf;
    private String warehouseName;

}
