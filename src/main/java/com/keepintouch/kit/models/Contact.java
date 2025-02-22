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

    public Contact(String id, String name, String email, String phoneNumber, String address, String picture, String description, boolean favorite, String websiteLink, String linkedInLink, List<SocialLink> links, User user) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.picture = picture;
        this.description = description;
        this.favorite = favorite;
        this.websiteLink = websiteLink;
        this.linkedInLink = linkedInLink;
        this.links = links;
        this.user = user;
    }

    public Contact() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public void setLinks(List<SocialLink> links) {
        this.links = links;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public List<SocialLink> getLinks() {
        return links;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", favorite=" + favorite +
                ", websiteLink='" + websiteLink + '\'' +
                ", linkedInLink='" + linkedInLink + '\'' +
                ", user=" + user +
                '}';
    }
}
