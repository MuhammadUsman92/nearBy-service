package com.muhammadusman92.nearbyservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150)
    private String subject;
    @Column(length = 500)
    private String detail;
    private boolean paid;
    @Column(length = 30)
    private String amount;
    private double startLatitude;
    private double endLatitude;
    private double startLongitude;
    private double endLongitude;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private boolean deleted = Boolean.FALSE;
}
