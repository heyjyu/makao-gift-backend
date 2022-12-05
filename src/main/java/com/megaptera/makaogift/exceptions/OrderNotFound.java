package com.megaptera.makaogift.exceptions;

public class OrderNotFound extends RuntimeException {
    public OrderNotFound(Long id) {
        super("Order not found: " + id);
    }
}
