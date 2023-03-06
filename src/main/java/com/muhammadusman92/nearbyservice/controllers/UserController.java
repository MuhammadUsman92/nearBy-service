package com.muhammadusman92.nearbyservice.controllers;
import com.muhammadusman92.nearbyservice.payload.Response;
import com.muhammadusman92.nearbyservice.payload.UserDto;
import com.muhammadusman92.nearbyservice.services.LocationService;
import com.muhammadusman92.nearbyservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    private FirebaseMessagingService firebaseService;
//
//    @PostMapping("/send-notification")
//    public String sendNotification(@RequestBody Note note, String s) throws FirebaseMessagingException {
//        return firebaseService.sendNotification(note, "cjzOCZjX9yqOCm_e9i4yM0:APA91bFPrVwKUryCw7NMaQE-3UPNB6p0TjnSUBRSjIt6z4zlrG8aqukAUY0wWJQWoA0xMTx7r0xnDtlo_Wv8oDE5tGGaTwc1asX1p6y_s3tkWRDskUtVdHnZylY4rrBAp5WxXUzGBfx7");
//    }
    @Autowired
    private UserService userService;
    @Autowired
    LocationService locationService;
    @PostMapping("/")
    public ResponseEntity<Response> createUser(@RequestBody UserDto userDto, HttpServletRequest request){
        String email = request.getHeader("username");
        userDto.setEmail(email);
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("User is successfully created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(savedUser)
                .build(), CREATED);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Response> updateUser(@RequestBody UserDto userDto,@PathVariable Integer userId, HttpServletRequest request){
        String email = request.getHeader("userEmail");
        userDto.setEmail(email);
        String name = request.getHeader("userName");
        userDto.setName(name);
        UserDto updateUser = userService.updateUser(userDto,userId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("User is successfully updated")
                .status(OK)
                .statusCode(OK.value())
                .data(updateUser)
                .build(),OK);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("User deleted successfully")
                .status(OK)
                .statusCode(OK.value())
                .build(),OK);
    }
    @GetMapping("/")
    public ResponseEntity<Response> getAllUsers(){
        List<UserDto> userDtoList = userService.getAllUsers();
        return new  ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("All Users are successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(userDtoList)
                .build(),OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<Response> getUserById(@PathVariable Integer userId){
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("User with id "+userId+" are successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(userDto)
                .build(),OK);
    }


//    @GetMapping("/{startLatitude}/{endLatitude}/{startLongitude}/{endLongitude}")
//    public ResponseEntity<Response> getAllLocations(
//           @PathVariable float startLatitude,
//           @PathVariable float endLatitude,
//           @PathVariable float startLongitude,
//           @PathVariable float endLongitude){
//        List<LocationDto> locationDtoList = locationService.getAllLocations(startLatitude, endLatitude, startLongitude, endLongitude);
//        return new  ResponseEntity<>(Response.builder()
//                .timeStamp(now())
//                .message("All Locations are successfully get")
//                .status(OK)
//                .statusCode(OK.value())
//                .data(locationDtoList)
//                .build(),OK);
//    }
}
