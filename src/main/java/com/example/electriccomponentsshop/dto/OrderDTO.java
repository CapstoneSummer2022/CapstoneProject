package com.example.electriccomponentsshop.dto;

import com.example.electriccomponentsshop.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
        private Integer id;
        private List<AccountEmail> accounts;
        private String status;
        private Double paidMoney;
        private Double totalPayment;


        @NoArgsConstructor
        @Getter
        @Setter
        @AllArgsConstructor
        public static class AccountEmail{
                private Integer id;
                private String email;
        }
}
