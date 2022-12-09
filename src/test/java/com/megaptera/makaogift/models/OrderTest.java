package com.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void totalPrice() {
        Order order = Order.fake();

        assertThat(order.totalPrice()).isEqualTo(order.unitPrice() * order.count());
    }
}
