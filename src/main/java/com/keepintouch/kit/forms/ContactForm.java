package com.keepintouch.kit.forms;

import com.keepintouch.kit.validators.ValidFile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.multipart.MultipartFile;

public class ContactForm {
    @NotBlank(message="Name is required!")
    private String name;

    @Email(message="Invalid email address!")
    @NotBlank(message="Email is required!")
    private String email;


    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number!")
    @NotBlank(message="Phone Number is required!")
    private String phoneNumber;

    @NotBlank(message = "Address is required!")
    private String address;

    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedInLink;
    @ValidFile(message="File not supported")
    private MultipartFile profileImage;

    public ContactForm() {
    }

    public ContactForm(String name, String email, String phoneNumber, String address, String description, boolean favorite, String websiteLink, String linkedInLink, MultipartFile profileImage) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.description = description;
        this.favorite = favorite;
        this.websiteLink = websiteLink;
        this.linkedInLink = linkedInLink;

        // TODO: Create custom annotation to validate file
        // size
        // resolution
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", favorite=" + favorite +
                ", websiteLink='" + websiteLink + '\'' +
                ", linkedInLink='" + linkedInLink + '\'' +
                '}';
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }

    public void setWebsiteLink(String websiteLink) {
        this.websiteLink = websiteLink;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public MultipartFile getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(MultipartFile profileImage) {
        this.profileImage = profileImage;
    }
}
