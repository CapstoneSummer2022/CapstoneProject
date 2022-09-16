package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SalesItemId implements Serializable {
    @Column(name="product_id")
    private Integer productId;
    @Column(name="inventory_report_id")
    private Integer inventoryReportId;
}
