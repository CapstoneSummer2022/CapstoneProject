package com.example.electriccomponentsshop.dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String id;
    @Email(message = "Email không hợp lệ",regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
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
    @NotEmpty(message = "Không được để trống")
    private String gender;
    @NotEmpty(message = "Không được để trống")
    private String dob;



}
