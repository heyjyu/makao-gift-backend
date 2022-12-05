package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.ExistingUsername;
import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateUserServiceTest {
    private CreateUserService createUserService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        createUserService = new CreateUserService(userRepository, passwordEncoder);
    }

    @Test
    void create() {
        String name = "홍길동";
        String username = "myid";
        String password = "Abcdef1!";
        String passwordCheck = "Abcdef1!";

        Long initialAmount = 50000L;

        User user = createUserService
                .create(name, username, password, passwordCheck);

        assertThat(user).isNotNull();
        assertThat(user.amount()).isEqualTo(initialAmount);
        verify(userRepository).save(user);
    }

    @Test
    void createWithExistingUsername() {
        String name = "홍길동";
        String username = "myid";
        String password = "Abcdef1!";
        String passwordCheck = "Abcdef1!";

        given(userRepository.existsByUsername(username))
                .willReturn(true);

        assertThrows(ExistingUsername.class,
                () -> createUserService.create(name, username, password, passwordCheck));
    }
}
