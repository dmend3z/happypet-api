package com.happypet.HappyPet.controller;

import com.happypet.HappyPet.command.user.CreateUserDto;
import com.happypet.HappyPet.command.user.UpdateUserDto;
import com.happypet.HappyPet.command.user.UserDetailsDto;
import com.happypet.HappyPet.enumerators.UserRole;
import com.happypet.HappyPet.service.UserServiceImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserServiceImp userService;

    public UserController(UserServiceImp userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {

        LOGGER.info("Request to create user - {} and role {}.", createUserDto, UserRole.USER);

        UserDetailsDto userDetailsDto = userService.createUser(createUserDto, UserRole.USER);

        LOGGER.info("Retrieving created user {}", createUserDto);

        return new ResponseEntity<>(userDetailsDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> getUserById(@PathVariable long userId) {

        UserDetailsDto userDetailsDto = userService.getUserById(userId);

        LOGGER.info("Retrieving user {}", userDetailsDto);

        return new ResponseEntity<>(userDetailsDto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> updateUser(@PathVariable long userId, @Valid @RequestBody UpdateUserDto updateUserDto) {

        UserDetailsDto userDetailsDto = userService.updateUser(userId, updateUserDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable long userId) {

        userService.deleteUser(userId);


        return new ResponseEntity(HttpStatus.OK);
    }
}
