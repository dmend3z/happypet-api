package com.happypet.HappyPet.service;

import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.controller.PetController;
import com.happypet.HappyPet.converter.PetConverter;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.DatabaseCommunicationException;
import com.happypet.HappyPet.exception.PetAlreadyExistsException;
import com.happypet.HappyPet.persistence.entity.PetEntity;
import com.happypet.HappyPet.persistence.repository.PetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImp implements PetService{
    private static final Logger LOGGER = LogManager.getLogger(PetService.class);

    PetRepository petRepository;

    public PetServiceImp(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetDetailsDto addNewPet(CreateOrUpdatePetDto petDetails, PrincipalDto principalDto)  {
        // Build Pet Entity
        PetEntity petEntity = PetConverter.fromCreateOrUpdatePetDtoToPetEntity(petDetails);
        petEntity.setUserId(principalDto.getUserId());
        petEntity.setActive(true);


        // Persist car int Database
        LOGGER.debug("Persisting new pet into database");
        try {
            petRepository.save(petEntity);
        }catch (Exception e) {
            LOGGER.error("Failed while saving pet into database {}", petDetails, e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        LOGGER.info("Retrieving the new pet with id {}", petEntity.getPetId());

        return PetConverter.fromPetEntityToPetDetailsDto(petEntity);
    }
}
