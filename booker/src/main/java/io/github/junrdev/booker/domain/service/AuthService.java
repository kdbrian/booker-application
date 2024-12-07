package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.auth.AuthRequest;
import io.github.junrdev.booker.domain.model.auth.AuthResponse;

public interface AuthService {// extends UserService{

    AuthResponse<UserDto> loginUser(AuthRequest request);

    AuthResponse<String> registerUser(UserDto userDto);

    AuthResponse<String> changePassword(UserDto userDto);

    AuthResponse<String> deleteAccount(UserDto userDto);
}
