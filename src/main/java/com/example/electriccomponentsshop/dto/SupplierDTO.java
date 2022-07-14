package com.example.electriccomponentsshop.dto;

import com.example.electriccomponentsshop.entities.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private String name;
    private String phone;
    private String mail;
    private String address;


}
