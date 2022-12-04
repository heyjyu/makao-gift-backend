package com.megaptera.makaogift.exceptions;

public class OrderFailed extends RuntimeException {
    public OrderFailed() {
        super("Order failed");
    }
}
