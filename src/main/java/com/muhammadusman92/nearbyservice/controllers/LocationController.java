package com.muhammadusman92.nearbyservice.controllers;

import com.muhammadusman92.nearbyservice.payload.LocationAndNotificationResponse;
import com.muhammadusman92.nearbyservice.payload.LocationDto;
import com.muhammadusman92.nearbyservice.payload.Response;
import com.muhammadusman92.nearbyservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @PostMapping("/")
    public ResponseEntity<Response> createLocation(@RequestBody LocationDto locationDto, HttpServletRequest request){
        String email = request.getHeader("username");
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
    public ResponseEntity<Response> updateLocation(@RequestBody LocationDto locationDto, HttpServletRequest request){
        String email = request.getHeader("userEmail");
        String userName = request.getHeader("userName");
        LocationAndNotificationResponse updateLocation = locationService.updateLocationOfUserEmail(locationDto,email,userName);
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
//    @GetMapping("/")
//    public ResponseEntity<Response> getAllLocations(){
//        List<LocationDto> LocationDtoList = LocationService.getAllLocations();
//        return new  ResponseEntity<>(Response.builder()
//                .timeStamp(now())
//                .message("All Locations are successfully get")
//                .status(OK)
//                .statusCode(OK.value())
//                .data(LocationDtoList)
//                .build(),OK);
//    }
    @GetMapping("/")
    public ResponseEntity<Response> getLocationById(HttpServletRequest request){
        String email = request.getHeader("username");
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
