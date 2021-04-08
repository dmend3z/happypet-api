package com.happypet.HappyPet.service;

import com.happypet.HappyPet.command.user.CreateUserDto;
import com.happypet.HappyPet.command.user.UpdateUserDto;
import com.happypet.HappyPet.command.user.UserDetailsDto;
import com.happypet.HappyPet.enumerators.UserRole;
import com.happypet.HappyPet.exception.UserAlreadyExistsException;
import com.happypet.HappyPet.exception.UserNotFoundException;

/**
 * Common interface for user services, provides methods to manage users
 */
public interface UserService {

    /**
     * Create new user
     * @param userRegistrationDto User data to create
     * @param userRole the type of the user (@see UserType)
     * @return the user created
     * @throws UserAlreadyExistsException when the user already exists
     */
    UserDetailsDto createUser(CreateUserDto userRegistrationDto, UserRole userRole) throws UserAlreadyExistsException;

    UserDetailsDto getUserById(long userId) throws UserNotFoundException;

    void deleteUser(long userId) throws UserNotFoundException;

    UserDetailsDto updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException;

}
