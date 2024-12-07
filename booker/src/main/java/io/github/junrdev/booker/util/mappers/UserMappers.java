package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class UserMappers extends EntityToDtoMapper<AppUser, UserDto> {

    @Override
    public AppUser fromDto(UserDto userDto) {
        return AppUser.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .build();
    }

    @Override
    public UserDto toDto(AppUser appUser) {
        return UserDto.builder()
                .uid(appUser.getUid())
                .name(appUser.getName())
                .email(appUser.getEmail())
                .phone(appUser.getPhone())
                .build();
    }
}
