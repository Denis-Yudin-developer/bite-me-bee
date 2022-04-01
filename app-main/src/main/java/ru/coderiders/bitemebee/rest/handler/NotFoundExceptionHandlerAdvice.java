package ru.coderiders.bitemebee.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class NotFoundExceptionHandlerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }
}
