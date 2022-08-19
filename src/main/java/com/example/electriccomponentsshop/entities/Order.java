package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_account", joinColumns = @JoinColumn(name = "orders_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts = new ArrayList<>();
    private Date orderedDate;
    private String receivedPerson;
    private String receivedPhone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pCode")
    private Province provinceOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dCode")
    private District districtOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wCode")
    private Ward wardOrder;
    private String detailLocation;
    @Column
    private String status;
    @Column
    private Double totalPayment;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();




}
