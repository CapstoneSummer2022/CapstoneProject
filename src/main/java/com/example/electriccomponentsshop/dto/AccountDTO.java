package com.example.electriccomponentsshop.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String id;
    @Email
    private String email;
    @NotEmpty(message = "Không được để trống")
    private String name;
    @NotEmpty(message = "Không được để trống")
    private String phone;
    @NotEmpty(message = "Không được để trống")
    private String role;
    @NotEmpty(message = "Không được để trống")
    private String password;
    @NotEmpty(message = "Không được để trống")
    private String provinceName;
    @NotEmpty(message = "Không được để trống")
    private String districtName;
    @NotEmpty(message = "Không được để trống")
    private String wardName;

    private String detailLocation;

    @NotEmpty(message = "Không được để trống")
    private String gender;
    @NotEmpty(message = "Không được để trống")
    private String dob;



}
