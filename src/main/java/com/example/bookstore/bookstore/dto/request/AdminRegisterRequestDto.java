package com.example.bookstore.bookstore.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdminRegisterRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
