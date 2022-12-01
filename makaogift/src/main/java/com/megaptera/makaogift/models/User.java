package com.megaptera.makaogift.models;

import com.megaptera.makaogift.dtos.UserCreationDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "PERSON")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String username;

    private String password;

    private Long amount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private static final Long INITIAL_AMOUNT = 50000L;

    public User() {
    }

    public User(Long id, String name, String username, String password, Long amount) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.amount = amount;
    }

    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public Long amount() {
        return amount;
    }

    public UserCreationDto toCreationDto() {
        return new UserCreationDto(id, name, username);
    }

    public static User fake() {
        return new User(1L, "홍길동", "myid", "Abcdef1!", 50000L);
    }

    public void setInitialAmount() {
        this.amount = INITIAL_AMOUNT;
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, this.password);
    }
}
