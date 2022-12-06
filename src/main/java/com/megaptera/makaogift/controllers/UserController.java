package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.CountUserService;
import com.megaptera.makaogift.application.CreateUserService;
import com.megaptera.makaogift.application.GetUserService;
import com.megaptera.makaogift.dtos.UserCountDto;
import com.megaptera.makaogift.dtos.UserCreationDto;
import com.megaptera.makaogift.dtos.UserDto;
import com.megaptera.makaogift.dtos.UserRegistrationDto;
import com.megaptera.makaogift.exceptions.ExistingUsername;
import com.megaptera.makaogift.exceptions.PasswordNotMatched;
import com.megaptera.makaogift.models.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private CreateUserService createUserService;
    private GetUserService getUserService;
    private CountUserService countUserService;

    public UserController(CreateUserService createUserService,
                          GetUserService getUserService,
                          CountUserService countUserService) {
        this.createUserService = createUserService;
        this.getUserService = getUserService;
        this.countUserService = countUserService;
    }

    @GetMapping("me")
    public UserDto user(@RequestAttribute Long userId) {
        User user = getUserService.getUser(userId);

        return user.toUserDto();
    }

    @GetMapping
    public UserCountDto userCount(@RequestParam boolean countOnly, String username) {
        if (countOnly) {
            return new UserCountDto(countUserService.count(username));
        }

        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto signUp(
            @Valid @RequestBody UserRegistrationDto userRegistrationDto) {
        String name = userRegistrationDto.getName();
        String username = userRegistrationDto.getUsername();
        String password = userRegistrationDto.getPassword();
        String passwordCheck = userRegistrationDto.getPasswordCheck();

        if (!password.equals(passwordCheck)) {
            throw new PasswordNotMatched();
        }

        User user = createUserService.create(name, username, password, passwordCheck);

        return user.toCreationDto();
    }

    @ExceptionHandler(ExistingUsername.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String existingUsername() {
        return "Username already exists!";
    }

    @ExceptionHandler(PasswordNotMatched.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordNotMatched() {
        return "Password not matched!";
    }
}
