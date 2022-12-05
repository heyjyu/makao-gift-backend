package com.megaptera.makaogift.models;

import com.megaptera.makaogift.dtos.OrderCreationDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    @Column(name = "sendingTo")
    private String to;

    private String address;

    private String message;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long id, Long userId, Long productId, Integer count, String to, String address, String message) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.count = count;
        this.to = to;
        this.address = address;
        this.message = message;
    }

    public Long id() {
        return id;
    }

    public Long userId() {
        return userId;
    }

    public Long productId() {
        return productId;
    }

    public Integer count() {
        return count;
    }

    public String to() {
        return to;
    }

    public String address() {
        return address;
    }

    public String message() {
        return message;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public static Order fake() {
        return new Order(1L, 1L, 1L, 1, "동길홍", "서울시 행복구 행복동", "행복하세요~");
    }

    public OrderCreationDto toCreationDto() {
        return new OrderCreationDto(id, productId, count);
    }

    public Long totalPrice(Long price) {
        return price * count;
    }
}
