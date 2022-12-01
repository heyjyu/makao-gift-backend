package com.megaptera.makaogift.repositories;

import com.megaptera.makaogift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByUsername(String username);
}
