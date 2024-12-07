package io.github.junrdev.booker.domain.service;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {


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

}
