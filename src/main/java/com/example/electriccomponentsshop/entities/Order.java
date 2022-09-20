package com.example.electriccomponentsshop.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name= "employee_id")

    private Account accountEmployee;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Account accountCustomer;
    @CreationTimestamp
    @Column(updatable = false)
    private Date orderedDate;
    private String receivedPerson;
    private String receivedPhone;
    private BigDecimal paidMoney;
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
    private BigDecimal totalPayment;
    @OneToMany(mappedBy = "order",fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_type_id")
    private OrderKind orderKind;
    @OneToOne(mappedBy = "order",optional = true)
    private ExportTransaction exportTransaction;

    private String paymentMethod;


}
