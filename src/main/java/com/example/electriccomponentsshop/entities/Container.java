package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String shelf;
    @Column
    private Integer rowIn;
    @Column
    private Integer columnIn;
    @Column
    private String tray;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="warehouse_id")
    private Warehouse warehouse;

}
