package ru.coderiders.BiteMeBee.rest.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Map<String, String>> messages = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(item -> errors.put(item.getField(), item.getDefaultMessage()));
        messages.put("messages", errors);
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
}
