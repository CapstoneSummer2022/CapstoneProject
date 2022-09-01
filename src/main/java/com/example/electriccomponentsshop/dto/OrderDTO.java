package com.example.electriccomponentsshop.dto;

import com.example.electriccomponentsshop.entities.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
        private String id;
        private List<AccountEmail> accounts;
    @JsonProperty("name")
    private String name;
    @JsonProperty("orderItemDtos")
    @NotEmpty(message = "not null")
private List<OrderItemDto> orderItemDtos;
        private String status;
        private Double totalPayment;
        private String orderedDate;
        private String receivedPerson;
        private String receivedPhone;
        private String provinceName;
        private String districtName;
        private String wardName;
        private String detailLocation;

        @NoArgsConstructor
        @Getter
        @Setter
        @AllArgsConstructor
        public static class AccountEmail{
                private Integer id;
                private String email;
        }
}
