package com.happypet.HappyPet.service;

import com.happypet.HappyPet.command.user.CreateUserDto;
import com.happypet.HappyPet.command.user.UpdateUserDto;
import com.happypet.HappyPet.command.user.UserDetailsDto;
import com.happypet.HappyPet.converter.UserConverter;
import com.happypet.HappyPet.enumerators.UserRole;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.UserAlreadyExistsException;
import com.happypet.HappyPet.exception.UserNotFoundException;
import com.happypet.HappyPet.persistence.entity.UserEntity;
import com.happypet.HappyPet.persistence.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDto createUser(CreateUserDto userRegistrationDto, UserRole userRole) throws UserAlreadyExistsException {
        LOGGER.debug("Creating user {} with role {}", userRegistrationDto, userRole);
        // Build UserEntity
        UserEntity userEntity = UserConverter.fromCreateUserDtoToUserEntity(userRegistrationDto);
        userEntity.setRole(userRole);

        // Create user on databse
        LOGGER.info("User created on DataBase");
        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException sqlException) {
            LOGGER.error(ErrorMessages.USER_ALREADY_EXISTS, sqlException);
            throw new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
        }
        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }

    @Override
    public UserDetailsDto getUserById(long userId) {

        // Get details from databse
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND));

        // Build UserDetailsDto to return the client
        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }

    @Override
    public void deleteUser(long userId) {

        // Verify if the user exists
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND));

        // Delete User
        userRepository.delete(userEntity);
    }

    @Override
    public UserDetailsDto updateUser(long userId, UpdateUserDto updateUserDto) throws UserNotFoundException {

        // Verify if the user exists
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND));

        // Update data with userDetails
        userEntity.setFirstName(updateUserDto.getFirstName());
        userEntity.setLastName(updateUserDto.getLastName());
        userEntity.setEmail(updateUserDto.getEmail());
        userEntity.setPhoneNumber(updateUserDto.getPhoneNumber());


        userRepository.save(userEntity);


        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }
}
