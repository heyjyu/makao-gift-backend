package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.UserNotFound;
import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetUserService {
    private UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        return user;
    }
}
