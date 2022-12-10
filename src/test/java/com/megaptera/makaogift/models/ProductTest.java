package com.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void creation() {
        Long id = 1L;
        String name = "치킨";
        String producer = "최고치킨";
        Long price = 10000L;
        String description = "세상에서 제일 맛있는 치킨";
        String imageUrl = "http://image.com";

        Product product = new Product(id, name, producer, price, description, imageUrl);

        assertThat(product.name()).isEqualTo("치킨");
    }
}
