package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="Import_Transaction")
public class ImportTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private Date importDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "importTransaction")
    List<ImportItem> importItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="warehouse_id")
    private Warehouse warehouseImport;



}
