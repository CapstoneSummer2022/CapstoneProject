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
@Table(name = "District")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dCode;
    private String name;
    private String type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pCode")
    private Province province;
    @OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
    List<Ward> wardList =new ArrayList<>();
    @OneToMany(mappedBy = "districtAccount",fetch = FetchType.LAZY)
    List<Account> accounts =new ArrayList<>();


}