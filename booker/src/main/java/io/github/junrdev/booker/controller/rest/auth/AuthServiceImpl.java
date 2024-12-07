package io.github.junrdev.booker.controller.rest.auth;

import io.github.junrdev.booker.config.JwtService;
import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.domain.model.auth.AuthRequest;
import io.github.junrdev.booker.domain.model.auth.AuthResponse;
import io.github.junrdev.booker.domain.service.AuthService;
import io.github.junrdev.booker.repo.mongo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;//user db functions
        this.jwtService = jwtService;//for generating tokens
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse<UserDto> loginUser(AuthRequest request) {
        //fetch user info

        //validate

        //return response
        return null;
    }

    @Override
    public AuthResponse<String> registerUser(UserDto userDto) {
        //save user
        var user = AppUser.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .dateJoined(userDto.getDateJoined())
                .lastUpdated(LocalDateTime.now().toString())
                .build();

        var saved = userRepository.save(user);
        return AuthResponse.<String>builder()
                .token(null)
                .data("Saved user with id : " + saved.getUid())
                .build();
    }

    @Override
    public AuthResponse<String> changePassword(UserDto userDto) {
        return null;
    }

    @Override
    public AuthResponse<String> deleteAccount(UserDto userDto) {
        return null;
    }
}
