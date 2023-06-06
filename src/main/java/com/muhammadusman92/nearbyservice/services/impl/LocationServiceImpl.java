package com.muhammadusman92.nearbyservice.services.impl;

import com.muhammadusman92.nearbyservice.config.ConversionDtos;
import com.muhammadusman92.nearbyservice.entity.Chat;
import com.muhammadusman92.nearbyservice.entity.Location;
import com.muhammadusman92.nearbyservice.entity.NotificationToBeSend;
import com.muhammadusman92.nearbyservice.entity.User;
import com.muhammadusman92.nearbyservice.exception.ResourceNotFoundException;
import com.muhammadusman92.nearbyservice.payload.LocationAndNotificationResponse;
import com.muhammadusman92.nearbyservice.payload.LocationDto;
import com.muhammadusman92.nearbyservice.repo.ChatRepo;
import com.muhammadusman92.nearbyservice.repo.LocationRepo;
import com.muhammadusman92.nearbyservice.repo.NotificationToBeSendRepo;
import com.muhammadusman92.nearbyservice.repo.UserRepo;
import com.muhammadusman92.nearbyservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private NotificationToBeSendRepo notificationToBeSendRepo;
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public LocationDto createLocationOfUserId(String userEmail,LocationDto locationDto) {
        Location location = ConversionDtos.locationDtoToLocation(locationDto);
        if(userRepo.existsUserByEmail(userEmail)){
            User findUser = userRepo.findByEmail(userEmail)
                    .orElseThrow(()->new ResourceNotFoundException("User","ID",userEmail));
            location.setUser(findUser);
            return ConversionDtos.locationToLocationDto(locationRepo.save(location));
        }
        User createUser = new User();
        createUser.setEmail(userEmail);
        createUser.setLocation(location);
        location.setUser(createUser);
        userRepo.save(createUser);
        return ConversionDtos.locationToLocationDto(createUser.getLocation());
    }
    @Override
    public LocationDto updateLocation(LocationDto locationDto, Integer locationId) {
        Location location = ConversionDtos.locationDtoToLocation(locationDto);
        Location findLocation = locationRepo.findById(locationId)
                .orElseThrow(()->new ResourceNotFoundException("Location","LocationId",locationId));
        location.setId(findLocation.getId());
        location.setUser(findLocation.getUser());
        return ConversionDtos.locationToLocationDto(locationRepo.save(location));
    }
    @Override
    public LocationAndNotificationResponse updateLocationOfUserEmail(LocationDto locationDto, String userEmail, String token) {
        Location location = ConversionDtos.locationDtoToLocation(locationDto);
        if(!(userRepo.existsUserByEmail(userEmail))){
            User createUser = new User();
            createUser.setEmail(userEmail);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization",  token);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            String apiUrl = "lb://AUTHENTICATION-SERVICE/api/v1/auth/user-email/"+userEmail;
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
            String userName = response.getBody();
            createUser.setUserName(userName);
            createUser.setLocation(location);
            location.setUser(createUser);
            userRepo.save(createUser);
        }
        Location findLocation = locationRepo.findByUserEmail(userEmail)
                .orElseThrow(()->new ResourceNotFoundException("User","Email",userEmail));
        location.setId(findLocation.getId());
        location.setUser(findLocation.getUser());
        LocationDto updateLocationDto = ConversionDtos.locationToLocationDto(locationRepo.save(location));
        List<NotificationToBeSend> allNotificationOfUser = notificationToBeSendRepo.findAllByUser(findLocation.getUser());
        LocationAndNotificationResponse response = new LocationAndNotificationResponse();
        response.setNotificationPresent(false);
        response.setLocationDto(locationDto);
        if(!allNotificationOfUser.isEmpty()){
            response.setNotificationPresent(true);
            response.setNotification(allNotificationOfUser.stream()
                    .map(ConversionDtos::notificationToNotificationDto
                    ).toList());
            for(NotificationToBeSend notificationToBeSend:allNotificationOfUser){
                notificationToBeSendRepo.delete(notificationToBeSend);
            }
        }
        List<Chat> chats = chatRepo.findByReceiverEmail(userEmail);
        response.setChatPresent(false);
        if(!chats.isEmpty()){
            response.setChatPresent(true);
            response.setChat(chats.stream().map(ConversionDtos::chatToChatDto).toList());
            for(Chat chat:chats){
                chatRepo.delete(chat);
            }
        }
        return response;
    }
    @Override
    public LocationDto getLocationOfUserEmail(String userEmail) {
        Location location = locationRepo.findByUserEmail(userEmail)
                .orElseThrow(()->new ResourceNotFoundException("User","Email",userEmail));
        return ConversionDtos.locationToLocationDto(location);
    }
    @Override
    public void deleteLocation(Integer locationId) {
        Location location = locationRepo.findById(locationId)
                .orElseThrow(()->new ResourceNotFoundException("Location","LocationId",locationId));
        locationRepo.delete(location);
    }
}
