package com.muhammadusman92.nearbyservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NotificationToBeSendDto {
    private Integer id;
    private String subject;
    private String detail;
    private boolean paid;
    private String amount;
    private String senderEmail;
}
