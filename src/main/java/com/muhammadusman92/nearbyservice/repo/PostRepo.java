package com.muhammadusman92.nearbyservice.repo;

import com.muhammadusman92.nearbyservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {
}
