package com.muhammadusman92.nearbyservice.config;

import com.muhammadusman92.nearbyservice.entity.*;
import com.muhammadusman92.nearbyservice.payload.*;

public class ConversionDtos {
    public static User userDtoToUser(UserDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        if(userDto.getLocation()!=null)
            user.setLocation(ConversionDtos.locationDtoToLocation(userDto.getLocation()));
        return user;
    }
    public static UserDto userToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        if(user.getLocation()!=null)
            userDto.setLocation(ConversionDtos.locationToLocationDto(user.getLocation()));
        return userDto;
    }
    public static Post postDtoToPost(PostDto postDto){
        Post post = new Post();
        post.setSubject(postDto.getSubject());
        post.setDetail(postDto.getDetail());
        post.setPaid(postDto.isPaid());
        post.setAmount(postDto.getAmount());
        post.setStartLatitude(postDto.getStartLatitude());
        post.setEndLatitude(postDto.getEndLatitude());
        post.setStartLongitude(postDto.getStartLongitude());
        post.setEndLongitude(postDto.getEndLongitude());
        return post;
    }
    public static PostDto postToPostDto(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setSubject(post.getSubject());
        postDto.setDetail(post.getDetail());
        post.setPaid(postDto.isPaid());
        post.setAmount(postDto.getAmount());
        postDto.setStartLatitude(post.getStartLatitude());
        postDto.setEndLatitude(post.getEndLatitude());
        postDto.setStartLongitude(post.getStartLongitude());
        postDto.setEndLongitude(post.getEndLongitude());
        return postDto;
    }
    public static Location locationDtoToLocation(LocationDto locationDto){
        Location location = new Location();
        location.setLastUpdateDateTime(locationDto.getLastUpdateDateTime());
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        return location;
    }
    public static LocationDto locationToLocationDto(Location location){
        LocationDto locationDto = new LocationDto();
        locationDto.setLastUpdateDateTime(location.getLastUpdateDateTime());
        locationDto.setId(location.getId());
        locationDto.setLatitude(location.getLatitude());
        locationDto.setLongitude(location.getLongitude());
        return locationDto;
    }

    public static NotificationToBeSendDto notificationToNotificationDto(NotificationToBeSend notificationToBeSend){
        NotificationToBeSendDto notificationToBeSendDto = new NotificationToBeSendDto();
        notificationToBeSendDto.setSubject(notificationToBeSend.getSubject());
        notificationToBeSendDto.setDetail(notificationToBeSend.getDetail());
        notificationToBeSendDto.setPaid(notificationToBeSend.isPaid());
        notificationToBeSendDto.setAmount(notificationToBeSend.getAmount());
        notificationToBeSendDto.setSenderEmail(notificationToBeSend.getSenderEmail());
        return notificationToBeSendDto;
    }

    public static Chat chatDtoToChat(ChatDto chatDto){
        Chat chat = new Chat();
        chat.setDate(chatDto.getDate());
        chat.setMessage(chatDto.getMessage());
        chat.setSenderEmail(chatDto.getSenderEmail());
        chat.setReceiverEmail(chatDto.getReceiverEmail());
        return chat;
    }
    public static ChatDto chatToChatDto(Chat chat){
        ChatDto chatDto = new ChatDto();
        chatDto.setId(chat.getId());
        chatDto.setDate(chat.getDate());
        chatDto.setMessage(chat.getMessage());
        chatDto.setSenderEmail(chat.getSenderEmail());
        chatDto.setReceiverEmail(chat.getReceiverEmail());
        return chatDto;
    }
}
