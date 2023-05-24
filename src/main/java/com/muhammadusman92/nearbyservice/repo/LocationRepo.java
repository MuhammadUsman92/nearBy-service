package com.muhammadusman92.nearbyservice.repo;

import com.muhammadusman92.nearbyservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepo extends JpaRepository<Location,Integer> {
    List<Location> findAllByLatitudeBetweenAndLongitudeBetween(
            double startLatitude, double endLatitude, double startLongitude, double endLongitude);


    Optional<Location> findByUserEmail(String userEmail);
}
