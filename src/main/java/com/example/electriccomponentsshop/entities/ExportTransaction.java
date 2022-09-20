package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Table(name="ExportTransaction")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExportTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date exportDate;
    @OneToOne(optional = true)
    @JoinColumn(name="order_id")
    private Order order;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "exportTransaction")
    private List<ExportItem> exportItems = new ArrayList<>();
    private String exportPerson;
    private String receivedPerson;
    private String description;

}
