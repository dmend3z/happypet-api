package com.happypet.HappyPet.persistence.repository;

import com.happypet.HappyPet.persistence.entity.PetColorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetColorRepository extends CrudRepository<PetColorEntity, Long> {

}
