package com.example.electriccomponentsshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String pCode;
    private String name;
    private String type;
    private String slug;
    @OneToMany(mappedBy = "province",fetch = FetchType.LAZY)
    @JsonIgnore
    List<District> districtList =new ArrayList<>();
    @OneToMany(mappedBy = "provinceAccount",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Account> accounts =new ArrayList<>();
    @OneToMany(mappedBy = "provinceOrder",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Order> orders =new ArrayList<>();
    @OneToMany(mappedBy = "provinceWarehouse",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Warehouse> warehouses =new ArrayList<>();

    @OneToMany(mappedBy = "provinceStore",fetch = FetchType.LAZY)
    @JsonIgnore
    List<StoreInformation> store =new ArrayList<>();
}
