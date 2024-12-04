package io.github.junrdev.booker.util.sec;

import io.github.junrdev.booker.domain.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.config.core.userdetails.

@Component

import java.util.Base64;
import java.util.Base64.Decoder;

public class JwtService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public String extractUserName(String token) {


        return null;
    }
}
