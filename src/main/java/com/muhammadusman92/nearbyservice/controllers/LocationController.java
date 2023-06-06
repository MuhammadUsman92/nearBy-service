package com.muhammadusman92.nearbyservice.controllers;

import com.muhammadusman92.nearbyservice.payload.LocationAndNotificationResponse;
import com.muhammadusman92.nearbyservice.payload.LocationDto;
import com.muhammadusman92.nearbyservice.payload.Response;
import com.muhammadusman92.nearbyservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @PostMapping("/")
    public ResponseEntity<Response> createLocation(@RequestHeader("authorities") String authorities,
                                                   @RequestBody LocationDto locationDto, HttpServletRequest request){
        String email = request.getHeader("userEmail");
        LocationDto savedLocation = locationService.createLocationOfUserId(email,locationDto);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Location is successfully created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(savedLocation)
                .build(), CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Response> updateLocation(@RequestHeader("Authorization") String token,@RequestHeader("userEmail") String userEmail,@RequestBody LocationDto locationDto, HttpServletRequest request){
        LocationAndNotificationResponse updateLocation = locationService.updateLocationOfUserEmail(locationDto,userEmail,token);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Location is successfully updated")
                .status(OK)
                .statusCode(OK.value())
                .data(updateLocation)
                .build(),OK);
    }
    @DeleteMapping("/{locationId}")
    public ResponseEntity<Response> deleteLocation(@PathVariable Integer locationId){
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Location deleted successfully")
                .status(OK)
                .statusCode(OK.value())
                .build(),OK);
    }
    @GetMapping("/")
    public ResponseEntity<Response> getLocationById(HttpServletRequest request){
        String email = request.getHeader("userEmail");
        LocationDto locationDto = locationService.getLocationOfUserEmail(email);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Location with email "+email+" are successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(locationDto)
                .build(),OK);
    }
}
