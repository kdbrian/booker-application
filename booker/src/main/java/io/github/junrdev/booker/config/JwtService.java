package io.github.junrdev.booker.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    @Value("${spring.security.secret.key}")
    String SECRET_KEY = "";

    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> resolver);

    Claims extractAllClaims(String token);

    SecretKey getSigningKey();

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);


    default String getSecretKey() {
        return SECRET_KEY;
    }
}
