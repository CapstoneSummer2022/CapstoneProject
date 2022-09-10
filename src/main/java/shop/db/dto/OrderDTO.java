package shop.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderDTO {
        private String id;
        private String accountEmployeeId;
        private String accountCustomerId;
        @NotEmpty(message = "not null")
        private List<OrderItemDto> orderItems;
        private String status;
        private Double totalPayment;
        private String orderedDate;
        @NotEmpty(message = "Không được để trống")
        private String receivedPerson;
        private String receivedPhone;
        private String provinceName;
        private String districtName;
        private String wardName;
        private String detailLocation;
        private Double paidMoney;
        @NoArgsConstructor
        @Getter
        @Setter
        @AllArgsConstructor
        public static class AccountEmail{
                private Integer id;
                private String email;
        }
}