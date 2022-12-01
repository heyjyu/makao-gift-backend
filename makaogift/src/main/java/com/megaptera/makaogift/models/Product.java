package com.megaptera.makaogift.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import com.megaptera.makaogift.dtos.ProductDto;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String producer;

    private Long price;

    private String description;

    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, String producer, Long price, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String producer() {
        return producer;
    }

    public Long price() {
        return price;
    }

    public String description() {
        return description;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public ProductDto toDto() {
        return new ProductDto(id, name, producer, price, description, imageUrl);
    }

    public static Product fake(Long id) {
        return new Product(id, "치킨", "최고치킨", 10000L, "세상에서 제일 맛있는 치킨", "http://image.com");
    }
}
