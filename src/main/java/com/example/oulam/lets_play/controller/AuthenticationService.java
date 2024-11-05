package com.example.oulam.lets_play.controller;

import com.example.oulam.lets_play.config.JwtService;
import com.example.oulam.lets_play.model.User;
import com.example.oulam.lets_play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                //.role(Role.USER)
                .build();

        repository.save(user);

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        UserDetails userDetails = userBuilder
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER") // Add user authorities here
                .build();

        var jwtToken = jwtService.createToken(userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        UserDetails userDetails = userBuilder
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();

        var jwtToken = jwtService.createToken(userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
