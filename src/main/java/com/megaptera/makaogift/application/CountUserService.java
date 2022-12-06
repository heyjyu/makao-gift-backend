package com.megaptera.makaogift.application;

import com.megaptera.makaogift.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CountUserService {
    private UserRepository userRepository;

    public CountUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer count(String username) {
        return userRepository.findAllByUsername(username).size();
    }
}
