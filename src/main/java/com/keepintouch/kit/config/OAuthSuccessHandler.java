package com.keepintouch.kit.config;

import com.keepintouch.kit.helpers.AppConstants;
import com.keepintouch.kit.models.Providers;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.repos.UserRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo repo;

    Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("Authentication success");
        // TODO: Save the data in the database before redirecting
        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        // create user and save in database
        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoles(List.of(AppConstants.ROLE_USER));
        user1.setAbout("Account created using Google login");

        // save the user in the database
        User user2 = (User) repo.findByEmail(email).orElse(null);
        if(user2 == null) {
            repo.save(user1);
            logger.info("New user created: "+email);
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
