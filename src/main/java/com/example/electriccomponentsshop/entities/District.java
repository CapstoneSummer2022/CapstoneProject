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
@Table(name = "District")
public class District {
    @Id
    private String dCode;
    private String name;
    private String type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pCode")
    private Province province;

    @OneToMany(mappedBy = "district",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Ward> wardList =new ArrayList<>();

    @OneToMany(mappedBy = "districtAccount",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Account> accounts =new ArrayList<>();

    @OneToMany(mappedBy = "districtOrder",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Order> orders =new ArrayList<>();

    @OneToMany(mappedBy = "districtWarehouse",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Warehouse> warehouses =new ArrayList<>();

    @OneToMany(mappedBy = "districtStore",fetch = FetchType.LAZY)
    @JsonIgnore
    List<StoreInformation> store =new ArrayList<>();
}
