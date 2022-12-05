package com.megaptera.makaogift.application;

import com.megaptera.makaogift.exceptions.ExistingUsername;
import com.megaptera.makaogift.models.User;
import com.megaptera.makaogift.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateUserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(
            String name, String username, String password, String passwordCheck) {
        if (userRepository.existsByUsername(username)) {
            throw new ExistingUsername(username);
        }

        User user = new User(name, username);
        user.changePassword(password, passwordEncoder);
        user.setInitialAmount();

        userRepository.save(user);

        return user;
    }
}
