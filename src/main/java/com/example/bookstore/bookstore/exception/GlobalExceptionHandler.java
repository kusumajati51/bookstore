package com.example.bookstore.bookstore.exception;

import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDto<Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(GlobalResponseDto.builder()
                .status(false)
                .message("Validation failed")
                .data(null)
                .error(errors)
                .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalResponseDto<Object>> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(GlobalResponseDto.builder()
                .status(false)
                .message("Validation error")
                .data(null)
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GlobalResponseDto<Object>> handleIllegalArg(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(GlobalResponseDto.builder()
                .status(false)
                .message("Bad request")
                .data(null)
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDto<Object>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalResponseDto.builder()
                .status(false)
                .message("Internal server error")
                .data(null)
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GlobalResponseDto<Object>> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalResponseDto.builder()
                .status(false)
                .message("Unauthorized: Invalid credentials")
                .data(null)
                .error(ex.getMessage())
                .build());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GlobalResponseDto<Object>> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(GlobalResponseDto.builder()
                .status(false)
                .message("Forbidden: You don't have permission to access this resource")
                .data(null)
                .error(ex.getMessage())
                .build());
    }

}
