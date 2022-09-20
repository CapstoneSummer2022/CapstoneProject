package com.example.electriccomponentsshop.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String accountEmployeeId;
    private String accountCustomerId;
    private String accountCustomerPhone;
    private String accountCustomerName;
    @NotEmpty(message = "not null")
    private List<OrderItemDTO> orderItems;
    private String status;
    private BigDecimal totalPayment;
    private String orderedDate;
    @NotEmpty(message = "Không được để trống")
    private String receivedPerson;
    private String receivedPhone;
    private String provinceName;
    private String districtName;
    private String wardName;
    private String detailLocation;
    private BigDecimal paidMoney;
    private String kindId;
    @NoArgsConstructor
    @Getter
    @Setter
    @AllArgsConstructor
    public static class AccountEmail {
        private Integer id;
        private String email;
    }
}
