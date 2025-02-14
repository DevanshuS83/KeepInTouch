package com.keepintouch.kit.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String userId;
    @Column(name="username", nullable=false)
    private String name;
    @Column(unique=true, nullable=false)
    private String email;
    private String password;
    @Column(length=10000, columnDefinition = "TEXT")
    private String about;
    @Column(length=10000, columnDefinition = "TEXT")
    private String profilePic;
    private String phoneNumber;
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    // SignUp method: goodle, facebook, twitter, linkedIn, self
    private Providers provider = Providers.SELF;
    private String providerUserId;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contact> contacts = new ArrayList<Contact>();
}
