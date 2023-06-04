package com.muhammadusman92.nearbyservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muhammadusman92.nearbyservice.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int id;
    private String subject;
    private String detail;
    private boolean paid;
    private String amount;
    private double startLatitude;
    private double endLatitude;
    private double startLongitude;
    private double endLongitude;
    private User user;
}
