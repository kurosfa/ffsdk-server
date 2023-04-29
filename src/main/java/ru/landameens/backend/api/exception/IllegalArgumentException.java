package ru.landameens.backend.api.exception;

public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException() {
    }

    public IllegalArgumentException(String message) {
        super(message);
    }
}