package com.happypet.HappyPet.controller;

import com.happypet.HappyPet.command.Paginated;
import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.enumerators.PetSpecie;
import com.happypet.HappyPet.error.ErrorMessages;
import com.happypet.HappyPet.exception.HappypetApiException;
import com.happypet.HappyPet.service.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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


    /**
     * Get pet by id
     * @param petId the pet id
     * @return the response entity
     */
    @GetMapping("/{petId}")
    public ResponseEntity<PetDetailsDto> getPetById(@PathVariable long petId) {
        LOGGER.info("Request to get pet by id - {}", petId);

        PetDetailsDto petDetails;
        try {
            petDetails = petService.getPetById(petId);

        } catch (HappypetApiException e) {
            throw e;

        } catch (Exception e) {
            LOGGER.error("Failed to get pet with id {}", petId, e);
            throw new HappypetApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Retrieving pet with id - {}", petDetails.getPetId());

        return new ResponseEntity<>(petDetails, HttpStatus.OK);
    }


    @GetMapping("/category")
    public ResponseEntity<Paginated<PetDetailsDto>> getPetsListByCategories(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(name = "category", defaultValue = "CAT") PetSpecie petSpecie) {
        LOGGER.info("Request to get pets list - page: {}, size: {}", page, size);

        Paginated<PetDetailsDto> petsList;
        try {
            petsList = petService.getPetsListByCategories(page, size, petSpecie);

        } catch (HappypetApiException e) {
            // Since RentacarApiException exceptions are thrown by us, we just throw them
            throw e;

        } catch (Exception e) {
            // With all others exceptions we log them and throw a generic exception
            LOGGER.error("Failed to get all pets", e);
            throw new HappypetApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Retrieving pets list");

        return new ResponseEntity<>(petsList, HttpStatus.OK);
    }

    /**
     * Get all cars by category
     * @return the response entity
     */
    @GetMapping()
    public ResponseEntity<Paginated<PetDetailsDto>> getPetsList(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "5") int size
                                                             ) {
        LOGGER.info("Request to get pets list - page: {}, size: {}", page, size);

        Paginated<PetDetailsDto> usersList;
        try {
            usersList = petService.getPetsList(page, size);

        } catch (HappypetApiException e) {
            // Since RentacarApiException exceptions are thrown by us, we just throw them
            throw e;

        } catch (Exception e) {
            // With all others exceptions we log them and throw a generic exception
            LOGGER.error("Failed to get all pets", e);
            throw new HappypetApiException(ErrorMessages.OPERATION_FAILED, e);
        }

        LOGGER.info("Retrieving pets list");

        return new ResponseEntity<>(usersList, HttpStatus.OK);
    }



}
