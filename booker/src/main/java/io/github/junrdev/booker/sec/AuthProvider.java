package io.github.junrdev.booker.sec;


import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.domain.service.UserService;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.AppUserToAuthorizedUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthProvider.class);
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AppUserToAuthorizedUserMapper userMapper;
    private final UserDetailsService userDetailsService;


    @Autowired
    AuthProvider(PasswordEncoder passwordEncoder, UserService userService, AppUserToAuthorizedUserMapper userMapper, UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userMapper.toDto(userService.getUserWithEmail(username));
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {

            String email = authentication.getName();
            String password = String.valueOf(authentication.getCredentials());
            LOGGER.debug("for email:{} password:{}", email, password);

            //loading user from user service
            var user = userDetailsService.loadUserByUsername(email);
            LOGGER.debug(" user:{} ", user);

            //checking account status and matching password
            if (user.isEnabled() && passwordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
            } else
                throw new BadCredentialsException("Something went wrong.");

        } catch (AppError error) {
            throw error;
        } catch (Exception e) {
            LOGGER.debug("failed due to {} , stack {}", e, Arrays.toString(e.getStackTrace()));
        }

        throw new AuthenticationCredentialsNotFoundException("Missing creds");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}