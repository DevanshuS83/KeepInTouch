package com.keepintouch.kit.controllers;

import com.keepintouch.kit.helpers.Helper;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
    }

    // user dashboard page
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // user add contact page
    @GetMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {
        return "user/profile";
    }
    // user view contact page
    // user edit contact page
    // user delete contact page
}
