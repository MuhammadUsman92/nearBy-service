package com.muhammadusman92.nearbyservice.entity;

import lombok.Generated;
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
@SQLDelete(sql = "UPDATE chat SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String senderEmail;
    @Column(length = 80)
    private String senderName;
    private String receiverEmail;
    @Column(length = 80)
    private String receiverName;
    private Date date;
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private boolean deleted = Boolean.FALSE;
}
