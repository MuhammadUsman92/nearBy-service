package com.muhammadusman92.nearbyservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(length = 100)
    private String userName;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Location location;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Post> postSet = new HashSet<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<NotificationToBeSend> notificationToBeSends = new HashSet<>();
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Chat> chats = new HashSet<>();
    private boolean deleted = Boolean.FALSE;
}
