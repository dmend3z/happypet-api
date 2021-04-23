package com.happypet.HappyPet.persistence.entity;

import com.happypet.HappyPet.enumerators.PetAge;
import com.happypet.HappyPet.enumerators.PetGender;
import com.happypet.HappyPet.enumerators.PetSpecie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long petId;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PetSpecie specie;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PetAge age;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PetGender gender;

    /*@ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "pets_colors",
            joinColumns =
            @JoinColumn(name = "colors_id"),
            inverseJoinColumns =
            @JoinColumn(name = "pet_id")
    )
    private List<PetColorEntity> petcolor;*/

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private long userId;

}
