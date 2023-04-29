package ru.landameens.backend.api.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }
}