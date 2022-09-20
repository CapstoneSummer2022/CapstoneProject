package com.example.electriccomponentsshop.common;

import java.util.Arrays;

public enum OrderEnum {
    PENDING(1, "Chờ Xử Lý"),
    CONFIRM(2, "Đã Xác Nhận"),
    DELIVERY(3, "Đang Giao Hàng"),
    DONE(4, "Hoàn Thành"),

    CANCEL(5,"Đã Hủy"),
    RETURNED(6,"Đã Nhận Hoàn");

    private final Integer id;
    private final String name;

    OrderEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static String getOrderEnum(Integer id) {
        OrderEnum orderEnum = Arrays.stream(OrderEnum.values()).filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        return orderEnum != null ? orderEnum.getName() : "";
    }
}
