package com.example.electriccomponentsshop.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImportItemId implements Serializable {
    @Column(name = "import_transaction_id")
    private Integer importTransactionId;
    @Column(name = "container_id")
    private Integer containerId;
    @Column(name = "product_id")
    private Integer productId;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImportItemId that = (ImportItemId) o;
        return productId.equals(that.productId) &&
                containerId.equals(that.containerId)&&productId.equals(that.importTransactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(importTransactionId,containerId,productId);
    }


}
