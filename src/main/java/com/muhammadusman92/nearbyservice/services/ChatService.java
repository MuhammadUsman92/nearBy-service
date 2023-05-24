package com.muhammadusman92.nearbyservice.services;

import com.muhammadusman92.nearbyservice.payload.ChatDto;

import java.util.List;

public interface ChatService {
    ChatDto createChat(ChatDto chatDto);
    List<ChatDto> getAllChatOfEmail(String email);
}
