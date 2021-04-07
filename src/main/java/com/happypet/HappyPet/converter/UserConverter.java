package com.happypet.HappyPet.converter;

import com.happypet.HappyPet.command.user.CreateUserDto;
import com.happypet.HappyPet.command.user.UserDetailsDto;
import com.happypet.HappyPet.persistence.entity.UserEntity;

public class UserConverter {

    public static UserEntity fromCreateUserDtoToUserEntity(CreateUserDto createUserDto) {
        return  UserEntity.builder()
                .firstName(createUserDto.getFirstName())
                .lastName(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .phoneNumber(createUserDto.getPhoneNumber())
                .password(createUserDto.getPassword())
                .build();
    }

    public static UserDetailsDto fromUserEntityToUserDetailsDto(UserEntity userEntity) {
        return  UserDetailsDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .build();
    }
}
