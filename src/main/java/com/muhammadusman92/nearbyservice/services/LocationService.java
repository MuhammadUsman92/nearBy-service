package com.muhammadusman92.nearbyservice.services;

import com.muhammadusman92.nearbyservice.payload.LocationAndNotificationResponse;
import com.muhammadusman92.nearbyservice.payload.LocationDto;

public interface LocationService {
    LocationDto createLocationOfUserId(String userEmail,LocationDto locationDto);
    LocationDto updateLocation(LocationDto locationDto,Integer locationId);
    LocationAndNotificationResponse updateLocationOfUserEmail(LocationDto locationDto, String userEmail, String token);
    LocationDto getLocationOfUserEmail(String userEmail);
//    List<LocationDto> getAllLocations(float startLatitude, float endLatitude, float startLongitude, float endLongitude);
    void deleteLocation(Integer locationId);
}
