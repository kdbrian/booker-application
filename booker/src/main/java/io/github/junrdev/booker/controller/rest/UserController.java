package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.domain.service.UserService;
import io.github.junrdev.booker.util.mappers.UserMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final UserMappers userMappers;

    @Autowired
    public UserController(UserService userService, UserMappers userMappers) {
        this.userService = userService;
        this.userMappers = userMappers;
    }


    @PostMapping("/new")
    public ResponseEntity<AppUser> addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers().stream().map(userMappers::toDto).toList());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserWithId(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(userMappers.toDto(userService.getUserWithId(userId)));
    }

    @GetMapping("")
    public ResponseEntity<UserDto> getUserWithEmail(
            @RequestParam(value = "email") String email
    ) {
        return ResponseEntity.ok(userMappers.toDto(userService.getUserWithEmail(email)));
    }


    @PatchMapping("/{userId}/update")
    public ResponseEntity<AppUser> updateUser(@RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<AppUser> deactivateUser(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.deactivateUser(userId));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllUsers() {
        long recordsAffected = userService.deleteUsers();
        return ResponseEntity.ok("Deleted " + recordsAffected + " records.");
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<String> deleteUser(
            @PathVariable String userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Deleted user " + userId + ".");
    }

}
