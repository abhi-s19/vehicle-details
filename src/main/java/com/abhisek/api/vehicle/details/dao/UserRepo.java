package com.abhisek.api.vehicle.details.dao;

import com.abhisek.api.vehicle.details.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUsername(String username);
}
