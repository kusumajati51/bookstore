package com.example.bookstore.bookstore.configuration;

import com.example.bookstore.bookstore.dto.response.GlobalResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        GlobalResponseDto<Object> body = GlobalResponseDto.builder()
                .status(false)
                .message("Forbidden: You don't have permission to access this resource")
                .data(null)
                .error(accessDeniedException.getMessage())
                .build();

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
