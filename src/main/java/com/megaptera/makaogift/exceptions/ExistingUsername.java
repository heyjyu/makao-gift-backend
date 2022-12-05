package com.megaptera.makaogift.exceptions;

public class ExistingUsername extends RuntimeException {
    public ExistingUsername(String username) {
        super("Existing username: " + username);
    }
}
