package com.megaptera.makaogift.models;

import com.megaptera.makaogift.exceptions.NotEnoughMoney;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {
    @Test
    void setInitialAmount() {
        Long initialAmount = 50000L;

        User user = new User();

        user.setInitialAmount();

        assertThat(user.amount()).isEqualTo(initialAmount);
    }

    @Test
    void authenticate() {
        PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);

        String password = "Abcdef1!";

        User user = new User();
        user.changePassword(password, passwordEncoder);

        assertTrue(user.authenticate(password, passwordEncoder));
    }

    @Test
    void order() {
        User user = new User();

        user.setInitialAmount();

        Long initialAmount = user.amount();

        Product product = Product.fake(1L);

        Integer count = 2;

        user.order(product, count);

        assertThat(user.amount()).isEqualTo(initialAmount - product.price() * count);
    }

    @Test
    void orderWithExceedingAmount() {
        User user = new User();

        user.setInitialAmount();

        Long initialAmount = user.amount();

        Product product = Product.fake(1L);

        Integer count = 10_000;

        assertThrows(NotEnoughMoney.class, () -> user.order(product, count));

        assertThat(user.amount()).isEqualTo(initialAmount);
    }
}
