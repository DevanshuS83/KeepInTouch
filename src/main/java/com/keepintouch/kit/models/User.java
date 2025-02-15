package com.keepintouch.kit.models;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
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
    @Enumerated(value=EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    private List<Contact> contacts = new ArrayList<Contact>();

    public User() {
    }

    public User(String userId, String name, String email, String password, String about, String profilePic, String phoneNumber, boolean enabled, boolean emailVerified, boolean phoneVerified, Providers provider, String providerUserId, List<Contact> contacts) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.profilePic = profilePic;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.contacts = contacts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public Providers getProvider() {
        return provider;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", enabled=" + enabled +
                ", emailVerified=" + emailVerified +
                ", phoneVerified=" + phoneVerified +
                ", provider=" + provider +
                ", providerUserId='" + providerUserId + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
