package com.happypet.HappyPet.persistence.repository;

import com.happypet.HappyPet.persistence.entity.PetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends PagingAndSortingRepository<PetEntity, Long> {

    @Query(value = "SELECT * FROM pets WHERE specie = :specie",
            countQuery = "SELECT * FROM pets WHERE specie = :specie",
            nativeQuery = true)
    Page<PetEntity> findAllBySpecie(@Param("specie") String specie, Pageable pageable);
}
