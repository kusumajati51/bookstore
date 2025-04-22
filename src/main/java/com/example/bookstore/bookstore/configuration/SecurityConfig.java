package com.example.bookstore.bookstore.configuration;

import com.example.bookstore.bookstore.middleware.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.http.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;

@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtMiddleware jwtMiddleware;
    private final AuthEntryPointJwt authEntryPointJwt;
    private final  AccessDeniedHandlerImpl accessDeniedHandlerImpl;

    public SecurityConfig(AuthenticationProvider authenticationProvider,
                          JwtMiddleware jwtMiddleware,
                          AuthEntryPointJwt authEntryPointJwt,
                          AccessDeniedHandlerImpl accessDeniedHandlerImpl) {
        this.authenticationProvider = authenticationProvider;
        this.jwtMiddleware = jwtMiddleware;
        this.authEntryPointJwt = authEntryPointJwt;
        this.accessDeniedHandlerImpl = accessDeniedHandlerImpl;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authEntryPointJwt)
                        .accessDeniedHandler(accessDeniedHandlerImpl)
                )
                .authenticationProvider(authenticationProvider)

                .addFilterBefore(jwtMiddleware, UsernamePasswordAuthenticationFilter.class)
        ; // ✅ Tambah ini


        return http.build();
    }
}
