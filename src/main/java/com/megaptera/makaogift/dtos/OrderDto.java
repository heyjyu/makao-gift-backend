package com.megaptera.makaogift.dtos;

import java.time.LocalDateTime;

public class OrderDto {
    private Long id;
    private ProductDto product;
    private Integer count;
    private Long totalPrice;
    private String to;
    private String address;
    private String message;
    private LocalDateTime createdAt;

    public OrderDto() {
    }

    public OrderDto(Long id, ProductDto product, Integer count, Long totalPrice,
                    String to, String address, String message, LocalDateTime createdAt) {
        this.id = id;
        this.product = product;
        this.count = count;
        this.totalPrice = totalPrice;
        this.to = to;
        this.address = address;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public Integer getCount() {
        return count;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public String getTo() {
        return to;
    }

    public String getAddress() {
        return address;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
