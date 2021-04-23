package com.happypet.HappyPet.service;

import com.happypet.HappyPet.command.user.CreateUserDto;
import com.happypet.HappyPet.command.user.UpdateUserDto;
import com.happypet.HappyPet.command.user.UserDetailsDto;
import com.happypet.HappyPet.converter.UserConverter;
import com.happypet.HappyPet.enumerators.UserRole;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.DatabaseCommunicationException;
import com.happypet.HappyPet.exception.UserAlreadyExistsException;
import com.happypet.HappyPet.exception.UserNotFoundException;
import com.happypet.HappyPet.persistence.entity.UserEntity;
import com.happypet.HappyPet.persistence.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetailsDto createUser(CreateUserDto userRegistrationDto, UserRole userRole) {

        // Build UserEntity
        UserEntity userEntity = UserConverter.fromCreateUserDtoToUserEntity(userRegistrationDto);
        userEntity.setRole(userRole);

        // Encrypt password
        String encryptedPassword = passwordEncoder.encode(userRegistrationDto.getPassword());
        // Set encrypted password
        userEntity.setEncryptedPassword(encryptedPassword);

        // Persist user into database
        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException sqlException) {
            LOGGER.error("Duplicated email - {}", userEntity.getEmail(), sqlException);
            throw new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
        } catch (Exception e) {
            LOGGER.error("Failed while saving user into database {}", userEntity, e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        // Build UserDetailsDto to return to the client
        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }

    @Override
    public UserDetailsDto getUserById(long userId) {
        // Get user details from database
        LOGGER.debug("Getting user with id {} from database", userId);
        UserEntity userEntity = getUserEntityById(userId);

        // Build UserDetailsDto to return to the client
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

    protected UserEntity getUserEntityById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    LOGGER.error("The user with id {} does not exist in database", userId);
                    return new UserNotFoundException(ErrorMessages.USER_NOT_FOUND);
                });
    }
}
