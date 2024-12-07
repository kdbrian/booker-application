package io.github.junrdev.booker.domain.model.auth;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Builder
@RequiredArgsConstructor
public class AuthRequest {
    private final String email;
    private final String password;
}
