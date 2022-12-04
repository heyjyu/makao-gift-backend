package com.megaptera.makaogift.application;

import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    private UserRepository userRepository;
    private GetUserService getUserService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUserService = new GetUserService(userRepository);
    }

    @Test
    void getUser() {
        given(userRepository.findById(1L))
                .willReturn(Optional.of(User.fake()));

        User user = getUserService.getUser(1L);

        assertThat(user).isNotNull();
    }
}
