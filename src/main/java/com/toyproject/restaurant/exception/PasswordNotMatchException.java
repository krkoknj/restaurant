package com.toyproject.restaurant.exception;

public class PasswordNotMatchException extends RuntimeException {
    PasswordNotMatchException() {

    }

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
