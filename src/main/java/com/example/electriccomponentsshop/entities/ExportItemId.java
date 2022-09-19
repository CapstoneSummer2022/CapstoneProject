package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExportItemId implements Serializable {
    @Column(name = "export_transaction_id")
    private Integer exportTransactionId;
    @Column(name = "sku_id")
    private String skuId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExportItemId that = (ExportItemId) o;
        return exportTransactionId.equals(that.exportTransactionId) &&
                skuId.equals(that.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exportTransactionId,skuId);
    }

}
