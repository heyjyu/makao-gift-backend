package com.megaptera.makaogift.dtos;

public class OrderCreationDto {
    private Long id;
    private Long productId;
    private Integer count;

    public OrderCreationDto() {
    }

    public OrderCreationDto(Long id, Long productId, Integer count) {
        this.id = id;
        this.productId = productId;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getCount() {
        return count;
    }
}
