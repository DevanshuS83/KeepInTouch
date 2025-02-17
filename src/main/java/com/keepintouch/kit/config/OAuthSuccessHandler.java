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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepo repo;

    Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("Authentication success");
        // identify the auth provider
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String authClientId = token.getAuthorizedClientRegistrationId();

        DefaultOAuth2User oAuth2user = (DefaultOAuth2User)authentication.getPrincipal();

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoles(List.of("ROLE_USER"));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("password");
        user.setAbout("Account created using Google");

        if(Objects.equals(authClientId, "google")){
            user.setEmail(oAuth2user.getAttribute("email"));
            user.setProfilePic(oAuth2user.getAttribute("picture"));
            user.setName(oAuth2user.getAttribute("name"));
            user.setProviderUserId(oAuth2user.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("Account created using Google");
        } else if(Objects.equals(authClientId, "github")){
            String email = oAuth2user.getAttribute("email") != null ? oAuth2user.getAttribute("email") : oAuth2user.getAttribute("login")+"@gmail.com";
            String picture = oAuth2user.getAttribute("avatar_url");
            String name = oAuth2user.getAttribute("login");
            String providerUserId = oAuth2user.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("Account created using Github");
        } else {
            logger.info("Auth Client ID not recognized");
        }

        // save the user in the database
        User user2 = (User) repo.findByEmail(user.getEmail()).orElse(null);
        if(user2 == null) {
            repo.save(user);
            logger.info("New user created: " + user.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
