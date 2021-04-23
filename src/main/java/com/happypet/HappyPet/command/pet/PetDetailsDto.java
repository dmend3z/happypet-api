package com.happypet.HappyPet.command.pet;

import com.happypet.HappyPet.enumerators.PetAge;
import com.happypet.HappyPet.enumerators.PetGender;
import com.happypet.HappyPet.enumerators.PetSpecie;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class PetDetailsDto {

    private long id;
    private String name;
    private PetSpecie specie;
    private PetAge age;
    private PetGender gender;
    //private List petcolor;
    private String image;
    private String description;
    private boolean active;
}
