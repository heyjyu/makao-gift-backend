package com.megaptera.makaogift.dtos;

import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.parameters.P;

public class UserRegistrationDto {
    @Pattern(regexp = "^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{3,7}$")
    private String name;

    @Pattern(regexp = "^[a-z|0-9]{4,16}$")
    private String username;

    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}")
    private String password;

    private String passwordCheck;

    public UserRegistrationDto() {
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }
}
