package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.service.AuthorisedUserDetailsManager;
import io.github.junrdev.booker.domain.service.UserService;
import io.github.junrdev.booker.util.mappers.AppUserToAuthorizedUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthorisedUserDetailsManagerImpl implements AuthorisedUserDetailsManager {

    private final UserService userService;
    private final AppUserToAuthorizedUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.toDto(userService.getUserWithEmail(username));
    }
}
