package com.muhammadusman92.nearbyservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE location SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Location {
    @Id
    @Column(name = "user_id")
    private Integer id;
    private double latitude;
    private double longitude;
    private Date lastUpdateDateTime;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
    private boolean deleted = Boolean.FALSE;
}
