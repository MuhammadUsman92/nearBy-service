package com.muhammadusman92.nearbyservice.services.impl;

import com.muhammadusman92.nearbyservice.config.ConversionDtos;
import com.muhammadusman92.nearbyservice.entity.Chat;
import com.muhammadusman92.nearbyservice.entity.User;
import com.muhammadusman92.nearbyservice.exception.ResourceNotFoundException;
import com.muhammadusman92.nearbyservice.payload.ChatDto;
import com.muhammadusman92.nearbyservice.repo.ChatRepo;
import com.muhammadusman92.nearbyservice.repo.UserRepo;
import com.muhammadusman92.nearbyservice.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public ChatDto createChat(ChatDto chatDto) {
        Chat chat = ConversionDtos.chatDtoToChat(chatDto);
        User user = userRepo.findByEmail(chat.getSenderEmail())
                .orElseThrow(()->new ResourceNotFoundException("User","Email",chat.getSenderEmail()));
        chat.setUser(user);
        return ConversionDtos.chatToChatDto(chatRepo.save(chat));
    }

    @Override
    public List<ChatDto> getAllChatOfEmail(String email) {
        List<Chat> chats = chatRepo.findByReceiverEmail(email);
        List<ChatDto> chatDtos = chats.stream().map(ConversionDtos::chatToChatDto).toList();
        for (Chat chat : chats)
        {
            chatRepo.delete(chat);
        }
        return chatDtos;
    }
}
