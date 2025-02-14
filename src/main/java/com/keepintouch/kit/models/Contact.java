package com.keepintouch.kit.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Contact {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String picture;
    @Column(length=10000, columnDefinition = "TEXT")
    private String description;
    private boolean favorite = false;
    private String websiteLink;
    private String linkedInLink;
    @OneToMany(mappedBy="contact", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<SocialLink>();
    @ManyToOne()
    private User user;
}
