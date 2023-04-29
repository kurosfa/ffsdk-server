package ru.landameens.backend.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<? extends ErrorResponse> onNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND.value())
                .body(ErrorResponse.of(
                        HttpStatus.NOT_FOUND.value(),
                        exception.getMessage()
                ));
    }

    @ExceptionHandler
    public ResponseEntity<? extends ErrorResponse> onInvalidDataException(InvalidDataException exception) {
        return badRequest(exception);
    }

    @ExceptionHandler
    public ResponseEntity<? extends ErrorResponse> onMissingRequestException(MissingServletRequestParameterException exception) {
        return badRequest(exception);
    }

    @ExceptionHandler
    public ResponseEntity<? extends ErrorResponse> onMismatchQueryParamRequestException(MethodArgumentTypeMismatchException exception) {
        return badRequest(exception);
    }

    @ExceptionHandler
    public ResponseEntity<? extends ErrorResponse> onIllegalArgumentException(IllegalArgumentException exception) {
        return badRequest(exception);
    }

    @ExceptionHandler
    public ResponseEntity<? extends ErrorResponse> onMethodNotValidException(MethodArgumentNotValidException exception) {
        StringBuilder builder = new StringBuilder("Errors:");
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            builder.append(" ").append(fieldName).append(" ").append(errorMessage).append(";");
        });

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .body(ErrorResponse.of(
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        builder.toString()
                ));
    }

    private ResponseEntity<? extends ErrorResponse> badRequest(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST.value())
                .body(ErrorResponse.of(
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage()
                ));
    }

    @Getter
    public static class ErrorResponse {
        private final int code;
        private final String text;

        private ErrorResponse(int code, String text) {
            this.code = code;
            this.text = text;
        }

        public static ErrorResponse of(int code, String text) {
            return new ErrorResponse(code, text);
        }
    }
}