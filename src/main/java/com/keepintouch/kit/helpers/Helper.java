package com.keepintouch.kit.helpers;

import com.keepintouch.kit.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.security.Principal;

public class Helper {
    UserRepo repo;

    public static String getEmailOfLoggedInUser(Authentication auth) {
        String username = "";
        // if logged in oauth2
        if(auth instanceof OAuth2AuthenticationToken) {

            var authToken = (OAuth2AuthenticationToken) auth;
            var clientId = authToken.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User)auth.getPrincipal();

            // if logged in with google
            if(clientId.equalsIgnoreCase("google")){
                username = oauth2User.getAttribute("email");
            } else {  // if logged in with github
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email") : oauth2User.getAttribute("login")+"@gmail.com";

            }
        } else // is logged in with username and password
            username = auth.getName();
        return username;
    }
}
