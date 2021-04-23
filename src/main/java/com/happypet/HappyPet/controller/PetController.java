package com.happypet.HappyPet.controller;

import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.HappypetApiException;
import com.happypet.HappyPet.service.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/pets")
public class PetController {
    private static final Logger LOGGER = LogManager.getLogger(PetController.class);

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<PetDetailsDto> createPet(@Valid @RequestBody CreateOrUpdatePetDto createOrUpdatePetDto, @AuthenticationPrincipal PrincipalDto principal) {

        LOGGER.info("Request to create pet - {}", createOrUpdatePetDto);
        PetDetailsDto petDetails;

        try {
            petDetails = petService.addNewPet(createOrUpdatePetDto, principal);

        } catch (HappypetApiException e) {
            throw e;
        } catch (Exception e) {
            LOGGER.error("Failed to created pet - {}", createOrUpdatePetDto, e);
            throw new HappypetApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        return new ResponseEntity<>(petDetails, HttpStatus.CREATED);
    }


}
