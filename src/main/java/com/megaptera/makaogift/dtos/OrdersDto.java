package com.megaptera.makaogift.dtos;

import java.util.List;

public class OrdersDto {
    private List<OrderDto> orders;
    private PageMetadataDto metadata;

    public OrdersDto() {
    }

    public OrdersDto(List<OrderDto> orders, PageMetadataDto pageMetadata) {
        this.orders = orders;
        this.metadata = pageMetadata;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public PageMetadataDto getMetadata() {
        return metadata;
    }
}
