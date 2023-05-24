package com.muhammadusman92.nearbyservice.repo;

import com.muhammadusman92.nearbyservice.entity.Chat;
import com.muhammadusman92.nearbyservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepo extends JpaRepository<Chat,Integer> {
    List<Chat> findByReceiverEmail(String receiverEmail);
}
