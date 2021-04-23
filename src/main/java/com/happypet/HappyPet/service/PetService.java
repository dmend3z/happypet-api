package com.happypet.HappyPet.service;


import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.exception.PetAlreadyExistsException;

public interface PetService {

    PetDetailsDto addNewPet(CreateOrUpdatePetDto petDetails, PrincipalDto principalDto);
}
