package io.github.junrdev.booker.controller.auth;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.auth.AuthRequest;
import io.github.junrdev.booker.domain.model.auth.AuthResponse;
import io.github.junrdev.booker.domain.service.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/users")
public class UsersAuthController {


    private static final Logger log = LoggerFactory.getLogger(UsersAuthController.class);
    private final AuthService authService;

    @Autowired
    public UsersAuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/no-auth")
    public String amATeaPot() {
        return "Hi, I am a tea pot.";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<UserDto>> loginUser(
            @Valid @RequestBody AuthRequest authRequest
    ) {
        var resp = authService.loginUser(authRequest);
        return ResponseEntity.ok(resp);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse<String>> registerUser(
            @Valid @RequestBody UserDto userDto
    ) {
        var resp = authService.registerUser(userDto);
        return ResponseEntity.ok(resp);
    }

}
