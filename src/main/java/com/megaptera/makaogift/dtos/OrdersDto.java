package com.megaptera.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderDto> orders;

    public OrdersDto() {
    }

    public OrdersDto(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }
}
