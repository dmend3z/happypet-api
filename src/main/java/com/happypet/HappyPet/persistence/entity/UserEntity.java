package com.happypet.HappyPet.persistence.entity;



import com.happypet.HappyPet.enumerators.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * The user entity
 */

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String encryptedPassword;

    @OneToMany(mappedBy = "petId")
    private List<PetEntity> pets;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserRole role;


}
