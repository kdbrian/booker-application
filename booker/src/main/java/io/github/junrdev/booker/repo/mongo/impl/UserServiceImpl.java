package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.UserDto;
import io.github.junrdev.booker.domain.model.AppUser;
import io.github.junrdev.booker.domain.service.UserService;
import io.github.junrdev.booker.repo.mongo.UserRepository;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.UserMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMappers userMappers;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMappers userMappers) {
        this.userRepository = userRepository;
        this.userMappers = userMappers;
    }

    @Override
    public AppUser addUser(UserDto dto) {
        return userRepository.save(userMappers.fromDto(dto));
    }

    @Override
    public List<AppUser> addUsers(List<UserDto> dto) {
        return userRepository.saveAll(dto.stream().map(userMappers::fromDto).toList());
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser updateUser(UserDto dto) {
        checkId(dto.getUid());

        //get user
        AppUser appUser = userRepository.findById(dto.getUid())
                .orElseThrow(() -> new AppError("Missing user with id " + dto.getUid(), HttpStatus.NOT_FOUND));

        //parse fields and update
        appUser.setEmail(dto.getEmail());
        appUser.setName(dto.getName());
        appUser.setPhone(dto.getPhone());

        //set last update
        appUser.setLastUpdated(LocalDateTime.now().toString());

        return userRepository.save(appUser);
    }

    @Override
    public AppUser getUserWithId(String uid) {
        checkId(uid);
        return userRepository.findById(uid)
                .orElseThrow(() -> new AppError("Missing user with id " + uid, HttpStatus.NOT_FOUND));
    }

    @Override
    public AppUser getUserWithEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new AppError("Missing user with id " + email, HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteUser(String userID) {
        checkId(userID);

        //get user
        AppUser appUser = userRepository.findById(userID)
                .orElseThrow(() -> new AppError("Missing user with id " + userID, HttpStatus.NOT_FOUND));

        userRepository.delete(appUser);
    }

    @Override
    public Long deleteUsers() {
        var count = userRepository.count();
        userRepository.deleteAll();
        return count;
    }

    @Override
    public AppUser deactivateUser(String uid) {
        checkId(uid);

        //get user
        AppUser appUser = userRepository.findById(uid)
                .orElseThrow(() -> new AppError("Missing user with id " + uid, HttpStatus.NOT_FOUND));

        appUser.setActive(false);

        return userRepository.save(appUser);
    }

}
