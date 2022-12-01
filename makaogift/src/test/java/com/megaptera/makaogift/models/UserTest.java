package com.megaptera.makaogift.models;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
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
}
