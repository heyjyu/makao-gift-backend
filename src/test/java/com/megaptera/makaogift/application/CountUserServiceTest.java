package com.megaptera.makaogift.application;

import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CountUserServiceTest {
    private CountUserService countUserService;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        countUserService = new CountUserService(userRepository);
    }

    @Test
    void countWithExistingUsername() {
        given(userRepository.findAllByUsername("myid"))
                .willReturn(List.of(User.fake()));

        Integer count = countUserService.count("myid");

        assertThat(count).isEqualTo(1);
    }

    @Test
    void countWithNotExistingUsername() {
        Integer count = countUserService.count("myid");

        assertThat(count).isEqualTo(0);
    }
}
