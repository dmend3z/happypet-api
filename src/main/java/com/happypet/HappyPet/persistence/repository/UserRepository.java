package com.happypet.HappyPet.persistence.repository;

import com.happypet.HappyPet.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
