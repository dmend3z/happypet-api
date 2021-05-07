package com.happypet.HappyPet.converter;

import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.persistence.entity.PetEntity;

public class PetConverter {

    public static PetDetailsDto fromPetEntityToPetDetailsDto(PetEntity petEntity) {
        return PetDetailsDto.builder()
                .petId(petEntity.getPetId())
                .name(petEntity.getName())
                .specie(petEntity.getSpecie())
                .age(petEntity.getAge())
                .gender(petEntity.getGender())
               // .petcolor(petEntity.getPetcolor())
                .image(petEntity.getImage())
                .description(petEntity.getDescription())
                .userId(petEntity.getUserId().getUserId())
                .active(petEntity.isActive())
                .build();

    }

    public static PetEntity fromCreateOrUpdatePetDtoToPetEntity(CreateOrUpdatePetDto createOrUpdatePetDto) {
        return PetEntity.builder()
                .name(createOrUpdatePetDto.getName())
                .specie(createOrUpdatePetDto.getSpecie())
                .age(createOrUpdatePetDto.getAge())
                .gender(createOrUpdatePetDto.getGender())
                //.petcolor(createOrUpdatePetDto.getPetcolor())
                .image(createOrUpdatePetDto.getImage())
                .description(createOrUpdatePetDto.getDescription())
                .build();
    }
}

