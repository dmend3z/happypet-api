package com.happypet.HappyPet.service;

import com.happypet.HappyPet.command.Paginated;
import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.controller.PetController;
import com.happypet.HappyPet.converter.PetConverter;
import com.happypet.HappyPet.enumerators.PetSpecie;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.DatabaseCommunicationException;
import com.happypet.HappyPet.exception.PetAlreadyExistsException;
import com.happypet.HappyPet.exception.PetNotFoundException;
import com.happypet.HappyPet.persistence.entity.PetEntity;
import com.happypet.HappyPet.persistence.entity.UserEntity;
import com.happypet.HappyPet.persistence.repository.PetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;


import java.util.ArrayList;
import java.util.List;

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

        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(principalDto.getUserId());
        petEntity.setUserId(userEntity);
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

    /**
     * @see PetService#getPetById(long)
     */
    @Override
    public PetDetailsDto getPetById(long petId) {
        // Get car from database
        LOGGER.debug("Getting pet with id {} from database", petId);
        PetEntity petEntity = petRepository.findById(petId)
                .orElseThrow(() -> {
                    LOGGER.error("Car with id {} does not exists in database", petId);
                    return new PetNotFoundException(ErrorMessages.PET_NOT_FOUND);
                });

        return PetConverter.fromPetEntityToPetDetailsDto(petEntity);
    }

    @Override
    public Paginated<PetDetailsDto> getPetsList(int page, int size) {
        // Get all users from database
        LOGGER.debug("Getting all pets from database");
        Page<PetEntity> petList;
        try {
            petList = petRepository.findAll(
                    PageRequest.of(page, size));
        } catch (Exception e) {
            LOGGER.error("Failed getting all pets from database", e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        // Convert list items from
        LOGGER.debug("Converting list of cars to PetDetailsDto");
        List<PetDetailsDto> petsListResponse = new ArrayList<>();
        for (PetEntity pet : petList.getContent()) {
            petsListResponse.add(PetConverter.fromPetEntityToPetDetailsDto(pet));
        }

        // Build custom paginated object
        Paginated<PetDetailsDto> results = new Paginated<>(
                petsListResponse,
                page,
                petsListResponse.size(),
                petList.getTotalPages(),
                petList.getTotalElements());

        return results;
    }


    @Override
    public Paginated<PetDetailsDto> getPetsListByCategories(int page, int size, PetSpecie petSpecie) {
        // Get all users from database
        LOGGER.debug("Getting all pets from database");
        Page<PetEntity> petList;
        try {
            petList = petRepository.findAllBySpecie(
                    petSpecie.name(),
                    PageRequest.of(page, size));
        } catch (Exception e) {
            LOGGER.error("Failed getting all pets from database", e);
            throw new DatabaseCommunicationException(ErrorMessages.DATABASE_COMMUNICATION_ERROR, e);
        }

        // Convert list items from
        LOGGER.debug("Converting list of cars to PetDetailsDto");
        List<PetDetailsDto> petsListResponse = new ArrayList<>();
        for (PetEntity pet : petList.getContent()) {
            petsListResponse.add(PetConverter.fromPetEntityToPetDetailsDto(pet));
        }

        // Build custom paginated object
        Paginated<PetDetailsDto> results = new Paginated<>(
                petsListResponse,
                page,
                petsListResponse.size(),
                petList.getTotalPages(),
                petList.getTotalElements());

        return results;
    }
}
