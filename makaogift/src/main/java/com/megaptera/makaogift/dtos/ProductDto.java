package com.megaptera.makaogift.dtos;

public class ProductDto {
    private Long id;
    private String name;
    private String producer;
    private Long price;
    private String description;
    private String imageUrl;

    public ProductDto(
            Long id, String name, String producer, Long price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
