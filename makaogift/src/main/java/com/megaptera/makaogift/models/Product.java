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

    public ProductDto toDto() {
        return new ProductDto(id, name, producer, price, description, imageUrl);
    }
}
