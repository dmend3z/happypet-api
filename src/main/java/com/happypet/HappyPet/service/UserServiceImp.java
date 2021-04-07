package com.happypet.HappyPet.service;

import com.happypet.HappyPet.command.user.CreateUserDto;
import com.happypet.HappyPet.command.user.UserDetailsDto;
import com.happypet.HappyPet.converter.UserConverter;
import com.happypet.HappyPet.enumerators.UserRole;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.UserAlreadyExistsException;
import com.happypet.HappyPet.persistence.entity.UserEntity;
import com.happypet.HappyPet.persistence.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    private  final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDto createUser(CreateUserDto userRegistrationDto, UserRole userRole) throws UserAlreadyExistsException {

        // Build UserEntity
        UserEntity userEntity = UserConverter.fromCreateUserDtoToUserEntity(userRegistrationDto);
        userEntity.setRole(userRole);
        try {
            userRepository.save(userEntity);
        }catch (DataIntegrityViolationException sqlException) {
            throw  new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
        }
        return UserConverter.fromUserEntityToUserDetailsDto(userEntity);
    }
}
