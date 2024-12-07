package io.github.junrdev.booker.domain.model.auth;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class AuthResponse<T> {
    private T data;
    private String token;
}
