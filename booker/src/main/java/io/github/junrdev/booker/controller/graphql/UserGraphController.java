package io.github.junrdev.booker.controller.graphql;


import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserGraphController {

    private final UserService userService;

    @Autowired
    public UserGraphController(UserService userService) {
        this.userService = userService;
    }

    @MutationMapping
    public AppUser addUser(@Valid @Argument UserDto dto) {
        return userService.addUser(dto);
    }


    @MutationMapping
    public List<AppUser> addUsers(@Valid @Argument List<UserDto> dto) {
        return userService.addUsers(dto);
    }

    @MutationMapping
    public AppUser updateUser(@Argument UserDto dto) {
        return userService.updateUser(dto);
    }


    @MutationMapping
    public Boolean deleteUser(@Argument String userId) {
        userService.deleteUser(userId);
        return true;
    }

    @MutationMapping
    public String deleteUsers() {
        var record = userService.deleteUsers();
        return String.format("Deleted %d records.", record);
    }

    @QueryMapping
    public List<AppUser> getUsers() {
        return userService.getUsers();
    }

    @QueryMapping
    public AppUser getUserWithId(@Argument String userId) {
        return userService.getUserWithId(userId);
    }

    @QueryMapping
    public AppUser getUserWithEmail(@Argument String email) {
        return userService.getUserWithEmail(email);
    }

}
