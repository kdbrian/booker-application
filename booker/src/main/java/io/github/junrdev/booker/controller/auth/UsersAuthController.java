package io.github.junrdev.booker.controller.auth;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.auth.AuthRequest;
import io.github.junrdev.booker.domain.model.auth.AuthResponse;
import io.github.junrdev.booker.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/users")
public class UsersAuthController {


    private final AuthService authService;

    @Autowired
    public UsersAuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse<UserDto>> loginUser(
            @RequestBody AuthRequest authRequest
    ) {
        var resp = authService.loginUser(authRequest);
        return ResponseEntity.ok(resp);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse<String>> registerUser(
            @RequestBody UserDto userDto
    ) {
        var resp = authService.registerUser(userDto);
        return ResponseEntity.ok(resp);
    }

}
