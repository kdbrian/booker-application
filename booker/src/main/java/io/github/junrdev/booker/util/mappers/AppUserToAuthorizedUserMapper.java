package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.sec.AuthorisedUser;

public class AppUserToAuthorizedUserMapper extends EntityToDtoMapper<AppUser, AuthorisedUser> {

    @Override
    public AppUser fromDto(AuthorisedUser authorisedUser) {
        return null;
    }

    @Override
    public AuthorisedUser toDto(AppUser user) {
        return new AuthorisedUser(user);
    }
}
