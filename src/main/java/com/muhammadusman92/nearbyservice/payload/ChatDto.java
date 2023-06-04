package com.muhammadusman92.nearbyservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class ChatDto {
    private Integer id;
    private String senderEmail;
    private String receiverEmail;
    private Date date;
    private String message;
}
