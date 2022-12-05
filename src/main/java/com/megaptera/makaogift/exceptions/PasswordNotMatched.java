package com.megaptera.makaogift.exceptions;

public class PasswordNotMatched extends RuntimeException {
    public PasswordNotMatched() {
        super("Password not matched");
    }
}
