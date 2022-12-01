package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.LoginFailed;
import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    private LoginService loginService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    LoginServiceTest() {
    }

    @BeforeEach
    void setup() {
        passwordEncoder = new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
        userRepository = mock(UserRepository.class);
        loginService = new LoginService(userRepository, passwordEncoder);
    }

    @Test
    void loginSuccess() {
        String username = "myid";
        String password = "Abcdef1!";

        User user = User.fake();
        user.changePassword(password, passwordEncoder);

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(user));

        assertThat(loginService.login(username, password).username())
                .isEqualTo(username);
    }

    @Test
    void loginWithWrongUsername() {
        String username = "myid";
        String password = "Abcdef1!";

        assertThrows(LoginFailed.class,
                () -> loginService.login(username, password));
    }

    @Test
    void loginWithWrongPassword() {
        String username = "myid";
        String password = "Abcdef1!";

        User user = User.fake();
        user.changePassword("xxx", passwordEncoder);

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(user));

        assertThrows(LoginFailed.class,
                () -> loginService.login(username, password));
    }
}
