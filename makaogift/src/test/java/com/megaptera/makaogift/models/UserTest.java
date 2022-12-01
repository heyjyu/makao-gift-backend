package com.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    void setInitialAmount() {
        Long initialAmount = 50000L;

        User user = new User();

        user.setInitialAmount();

        assertThat(user.amount()).isEqualTo(initialAmount);
    }
}
