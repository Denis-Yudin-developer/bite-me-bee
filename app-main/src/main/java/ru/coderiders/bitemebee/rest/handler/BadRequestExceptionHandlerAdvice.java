package ru.coderiders.BiteMeBee.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.coderiders.commons.rest.exception.BadRequestException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BadRequestExceptionHandlerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        Map<String, String> messages = new HashMap<>();
        messages.put("messages", e.getMessage());
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
}
