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
@Table(name = "Ward")
public class Ward {
    @Id
    private String wCode;
    private String name;
    private String type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dCode")
    private District district;
    @OneToMany(mappedBy = "wardAccount",fetch = FetchType.LAZY)
    List<Account> accounts =new ArrayList<>();
    @OneToMany(mappedBy = "wardOrder",fetch = FetchType.LAZY)
    List<Order> orders =new ArrayList<>();
    @OneToMany(mappedBy = "wardWarehouse",fetch = FetchType.LAZY)
    List<Warehouse> warehouses =new ArrayList<>();
    @OneToMany(mappedBy = "wardStore",fetch = FetchType.LAZY)
    List<StoreInformation> store =new ArrayList<>();
}
