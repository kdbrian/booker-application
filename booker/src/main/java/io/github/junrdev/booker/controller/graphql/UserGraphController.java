package io.github.junrdev.booker.controller.graphql;


import io.github.junrdev.booker.domain.dto.CompanyDTO;
import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.service.UserService;
import io.github.junrdev.booker.util.error.AppError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserGraphController {

    private final UserService userService;
    private final Validator validator;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserGraphController.class);

    @Autowired
    public UserGraphController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @MutationMapping
    public AppUser addUser(@Valid @Argument UserDto dto) {

        Set<ConstraintViolation<UserDto>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            String validatorError = violations.stream().map(v -> "\n" + v.getMessage()).collect(Collectors.joining());
            throw new AppError("Failed to validate request : Error \n" + validatorError, HttpStatus.BAD_REQUEST);
        }
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


    @MutationMapping
    public Boolean deactivateUser(@Argument String userId) {
        return userService.deactivateUser(userId) != null;
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
