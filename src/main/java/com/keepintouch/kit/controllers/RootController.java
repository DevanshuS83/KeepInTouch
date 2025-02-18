package com.keepintouch.kit.controllers;

import com.keepintouch.kit.helpers.Helper;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    @Autowired
    UserService userService;

    Logger log = LoggerFactory.getLogger(RootController.class);

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        /**
         * This method will be called for each and every request in the project.
         */
        if(authentication == null)
            return;
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);

    }
}
