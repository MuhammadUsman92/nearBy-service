package com.muhammadusman92.nearbyservice.repo;

import com.muhammadusman92.nearbyservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    boolean existsUserByEmail(String email);
    Optional<User> findByEmail(String userEmail);
}
