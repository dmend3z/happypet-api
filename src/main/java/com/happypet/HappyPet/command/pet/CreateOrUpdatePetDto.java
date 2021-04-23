package com.happypet.HappyPet.command.pet;

import com.happypet.HappyPet.enumerators.PetAge;
import com.happypet.HappyPet.enumerators.PetGender;
import com.happypet.HappyPet.enumerators.PetSpecie;
import lombok.Builder;
import lombok.Data;
import java.util.List;


import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateOrUpdatePetDto {

    @NotBlank(message = "Must have name")
    private String name;

    @NotBlank(message = "Must have specie")
    private PetSpecie specie;

    @NotBlank(message = "Must have age")
    private PetAge age;

    @NotBlank(message = "Must have gender")
    private PetGender gender;

  //  @NotBlank(message = "Must have Color")
   // private List<Long> petcolor;

    @NotBlank(message = "Must have image")
    private String image;

    @NotBlank(message = "Must have description")
    private String description;



}
