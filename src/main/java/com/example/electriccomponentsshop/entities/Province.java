package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pCode;
    private String name;
    private String type;
    @OneToMany(mappedBy = "province",fetch = FetchType.LAZY)
    List<District> districtList =new ArrayList<>();
    @OneToMany(mappedBy = "provinceAccount",fetch = FetchType.LAZY)
    List<Account> accounts =new ArrayList<>();

}
