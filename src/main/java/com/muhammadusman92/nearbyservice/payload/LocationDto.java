package com.muhammadusman92.nearbyservice.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.muhammadusman92.nearbyservice.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LocationDto {
    private Integer id;
    private double latitude;
    private double longitude;
    private Date lastUpdateDateTime;
    private User user;
}
