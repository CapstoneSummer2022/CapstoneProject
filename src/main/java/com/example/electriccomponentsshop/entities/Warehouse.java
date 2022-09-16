package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer storeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pCode")
    private Province provinceWarehouse;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dCode")
    private District districtWarehouse;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wCode")
    private Ward wardWarehouse;
    @Column
    private String detailLocation;
    @Column
    private Integer status;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouse")
    List<Container> containerList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "warehouseImport")
    List<ImportTransaction> importTransactions = new ArrayList<>();


}
