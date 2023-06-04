package com.muhammadusman92.nearbyservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LocationAndNotificationResponse {
    private LocationDto locationDto;
    private boolean notificationPresent;
    private List<NotificationToBeSendDto> notification = new ArrayList<>();
    private boolean chatPresent;
    private List<ChatDto> chat = new ArrayList<>();
}
