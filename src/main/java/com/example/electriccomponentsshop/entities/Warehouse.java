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
@Table(name="Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer storeId;
    private String status;
    @Column
    private String address;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "warehouse")
    List<Container> containerList = new ArrayList<>();


}
