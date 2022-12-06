package com.megaptera.makaogift.repositories;

import com.megaptera.makaogift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

    List<User> findAllByUsername(String username);
}
