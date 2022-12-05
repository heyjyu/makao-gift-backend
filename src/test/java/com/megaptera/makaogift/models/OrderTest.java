package com.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void totalPrice() {
        Order order = Order.fake();

        Long unitPrice = 10000L;

        assertThat(order.totalPrice(unitPrice)).isEqualTo(unitPrice * order.count());
    }
}
