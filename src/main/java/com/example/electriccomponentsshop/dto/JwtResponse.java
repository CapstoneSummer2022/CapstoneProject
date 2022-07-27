package com.example.electriccomponentsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type= "Bearer";
    private String refreshToken;
    private Integer id;
    private String email;
    private List<String> roles;


}
