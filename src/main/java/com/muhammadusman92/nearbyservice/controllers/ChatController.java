package com.muhammadusman92.nearbyservice.controllers;
import com.muhammadusman92.nearbyservice.payload.ChatDto;
import com.muhammadusman92.nearbyservice.payload.LocationDto;
import com.muhammadusman92.nearbyservice.payload.Response;
import com.muhammadusman92.nearbyservice.services.ChatService;
import com.muhammadusman92.nearbyservice.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @PostMapping("/")
    public ResponseEntity<Response> createChat(@RequestBody ChatDto chatDto, HttpServletRequest request){
        ChatDto chat = chatService.createChat(chatDto);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("Chat is successfully created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .data(chat)
                .build(), CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<Response> getAllChat(HttpServletRequest request){
        String email = request.getHeader("userEmail");
        List<ChatDto> chats = chatService.getAllChatOfEmail(email);
        return new ResponseEntity<>(Response.builder()
                .timeStamp(now())
                .message("All Chats are successfully get")
                .status(OK)
                .statusCode(OK.value())
                .data(chats)
                .build(),OK);
    }
}
