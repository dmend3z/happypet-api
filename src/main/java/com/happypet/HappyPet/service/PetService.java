package com.happypet.HappyPet.service;


import com.happypet.HappyPet.command.Paginated;
import com.happypet.HappyPet.command.auth.PrincipalDto;
import com.happypet.HappyPet.command.pet.CreateOrUpdatePetDto;
import com.happypet.HappyPet.command.pet.PetDetailsDto;
import com.happypet.HappyPet.enumerators.PetSpecie;
import com.happypet.HappyPet.exception.PetNotFoundException;

public interface PetService {

    PetDetailsDto addNewPet(CreateOrUpdatePetDto petDetails, PrincipalDto principalDto);

    PetDetailsDto getPetById(long petId) throws PetNotFoundException;

    Paginated<PetDetailsDto> getPetsList(int page, int size);
    Paginated<PetDetailsDto> getPetsListByCategories(int page, int size, PetSpecie petSpecie);



}
