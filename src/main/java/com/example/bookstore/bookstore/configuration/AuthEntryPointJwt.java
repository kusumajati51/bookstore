package com.example.bookstore.bookstore.configuration;

import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        GlobalResponseDto<Object> body = GlobalResponseDto.builder()
                .status(false)
                .message("Unauthorized: Token is missing or invalid")
                .data(null)
                .error(authException.getMessage())
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
