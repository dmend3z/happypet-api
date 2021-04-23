package com.happypet.HappyPet.persistence.repository;

import com.happypet.HappyPet.persistence.entity.PetEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends PagingAndSortingRepository<PetEntity, Long> {
}
