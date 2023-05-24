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
@SQLDelete(sql = "UPDATE notification_to_be_send SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class NotificationToBeSend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 150)
    private String subject;
    @Column(length = 500)
    private String detail;
    private boolean paid;
    @Column(length = 30)
    private String amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String senderEmail;
    private boolean deleted = Boolean.FALSE;
}
