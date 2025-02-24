package com.keepintouch.kit.controllers;

import com.keepintouch.kit.forms.ContactForm;
import com.keepintouch.kit.helpers.Helper;
import com.keepintouch.kit.helpers.Message;
import com.keepintouch.kit.helpers.MessageType;
import com.keepintouch.kit.models.Contact;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.services.ContactService;
import com.keepintouch.kit.services.ImageService;
import com.keepintouch.kit.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    @Autowired
    UserService userService;

    @Autowired
    private ImageService imageService;

    // add contact page
    @GetMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/addContact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result, Authentication auth, HttpSession session){
        Message message = new Message();
        if(result.hasErrors()){
            message.setContent("Please correct the following errors: ");
            message.setType(MessageType.red);
            session.setAttribute("message", message);
            return "user/addContact";
        }

        String username = Helper.getEmailOfLoggedInUser(auth);
        User user = userService.getUserByEmail(username);

        String filename = UUID.randomUUID().toString();
        String fileUrl = imageService.uploadImage(contactForm.getProfileImage(), filename);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setAddress(contactForm.getAddress());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setDescription(contactForm.getDescription());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setPicture(fileUrl);
        contact.setCloudinaryImagePublicId(filename);
        contact.setUser(user);

        contactService.save(contact);
        message.setContent("Contact added successfully");
        message.setType(MessageType.green);
        session.setAttribute("message", message);
        return "redirect:/user/contacts/add";
    }
}
