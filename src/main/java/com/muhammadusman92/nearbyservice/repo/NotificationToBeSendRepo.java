package com.muhammadusman92.nearbyservice.repo;

import com.muhammadusman92.nearbyservice.entity.NotificationToBeSend;
import com.muhammadusman92.nearbyservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationToBeSendRepo extends JpaRepository<NotificationToBeSend,Integer> {
    List<NotificationToBeSend> findAllByUser(User user);
}
