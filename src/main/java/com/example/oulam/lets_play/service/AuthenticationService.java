package com.example.oulam.lets_play.service;

import com.example.oulam.lets_play.config.JwtService;
import com.example.oulam.lets_play.dto.response.AuthenticationResponse;
import com.example.oulam.lets_play.dto.request.LoginRequest;
import com.example.oulam.lets_play.dto.request.RegisterRequest;
import com.example.oulam.lets_play.model.User;
import com.example.oulam.lets_play.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        UserDetails userDetails = userBuilder
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().toUpperCase())
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
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.builder();
        UserDetails userDetails = userBuilder
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole().toUpperCase())
                .build();

        var jwtToken = jwtService.createToken(userDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
