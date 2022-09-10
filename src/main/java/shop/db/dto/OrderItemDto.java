package shop.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDto {
    private String orderId;
    private String productId;
    private String productName;
    private Double subTotal;
    private String quantity;
    private String unitPrice;
}