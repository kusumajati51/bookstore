package com.example.bookstore.bookstore.service;

import com.example.bookstore.bookstore.dto.request.*;
import com.example.bookstore.bookstore.model.UserModel;
import com.example.bookstore.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequestDto request) {
        String encoded = passwordEncoder.encode(request.getPassword());

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email Already Register ");
        }

        UserModel user = UserModel.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(encoded)
                .isAdmin(false)
                .build();

        userRepository.save(user);

        return jwtService.generateToken(user);
    }

    public String login(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid email or password");
        }

        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));

        return jwtService.generateToken(user);
    }

    public String registerAdmin(AdminRegisterRequestDto request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        UserModel user = UserModel.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isAdmin(true)
                .build();

        userRepository.save(user);

        return jwtService.generateToken(user);
    }
}
