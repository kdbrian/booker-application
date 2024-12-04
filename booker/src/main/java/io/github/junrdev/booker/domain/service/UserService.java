package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.util.error.AppError;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AppUser addUser(UserDto dto);

    List<AppUser> addUsers(List<UserDto> dto);

    List<AppUser> getUsers();

    AppUser updateUser(UserDto dto);

    AppUser getUserWithId(String uid);

    AppUser getUserWithEmail(String email);


    void deleteUser(String userId);

    Long deleteUsers();

    AppUser deactivateUser(String uid);


    default void checkId(String id) {
        if (!ObjectId.isValid(id))
            throw new AppError("Invalid user id : " + id, HttpStatus.BAD_REQUEST);
    }

}
