package com.megaptera.makaogift.exceptions;

public class LoginFailed extends RuntimeException {
    public LoginFailed() {
        super("Login failed");
    }
}
