package com.happypet.HappyPet.persistence.entity;

import com.happypet.HappyPet.enumerators.PetColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "colors")
public class PetColorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long colorsId;

    @Column(nullable = false)
    private String name;


    /*@ManyToMany(mappedBy = "petcolor", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<PetEntity> pets;*/

}
