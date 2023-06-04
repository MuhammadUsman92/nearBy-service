package com.muhammadusman92.nearbyservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muhammadusman92.nearbyservice.entity.Location;
import com.muhammadusman92.nearbyservice.entity.NotificationToBeSend;
import com.muhammadusman92.nearbyservice.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int id;
    private LocationDto location;
    private String email;
    private String name;
    private Set<PostDto> postSet=new HashSet<>();
    private Set<NotificationToBeSend> notificationToBeSends = new HashSet<>();
}
