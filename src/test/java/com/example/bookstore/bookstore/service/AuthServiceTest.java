package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.RegisterRequestDto;
import com.example.bookstore.bookstore.model.UserModel;
import com.example.bookstore.bookstore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthenticationManager authenticationManager;

    private final String dummyToken = "mock-token";

    @Test
    void login_success_shouldReturnToken() {
        String email = "test@example.com";
        String password = "password";
        String hashed = "hashed-password";

        UserModel user = UserModel.builder()
                .email(email)
                .password(hashed)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any()))
                .thenReturn(new UsernamePasswordAuthenticationToken(email, password));
        when(jwtService.generateToken(user)).thenReturn(dummyToken);

        String token = authService.login(email, password);

        assertThat(token).isEqualTo(dummyToken);
    }

    @Test
    void login_invalidPassword_shouldThrowException() {
        String email = "test@example.com";
        String password = "wrong";

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid email or password"));

        assertThatThrownBy(() -> authService.login(email, password))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid email or password");
    }

    @Test
    void login_userNotFound_shouldThrowException() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authService.login("notfound@example.com", "password"))
                .isInstanceOf(BadCredentialsException.class)
                .hasMessage("Invalid email or password");
    }

    @Test
    void register_success_shouldReturnToken() {
        RegisterRequestDto request = new RegisterRequestDto();
        request.setEmail("new@example.com");
        request.setName("New User");
        request.setPassword("rawpass");

        UserModel savedUser = UserModel.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password("encodedpass")
                .build();

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedpass");
        when(userRepository.save(any())).thenReturn(savedUser);
        when(jwtService.generateToken(savedUser)).thenReturn(dummyToken);

        String token = authService.register(request);

        assertThat(token).isEqualTo(dummyToken);
    }
}
