package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Account account;
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;
    @Column
    private String content;

}
