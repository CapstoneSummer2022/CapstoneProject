package com.example.electriccomponentsshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ExportItem")
public class ExportItem {
    @EmbeddedId
    private ExportItemId exportItemId;
    private BigInteger quantity;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("skuId")
    @JoinColumn(name = "sku_id", referencedColumnName = "id")
    private Sku sku;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("exportTransactionId")
    @JoinColumn(name = "export_transaction_id")
    private ExportTransaction exportTransaction;

}
