package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMappers extends EntityToDtoMapper<AppUser, UserDto> {

    @Override
    public AppUser fromDto(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto toDto(AppUser appUser) {
        return null;
    }
}
