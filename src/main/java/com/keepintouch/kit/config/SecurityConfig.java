package com.keepintouch.kit.config;

import com.keepintouch.kit.services.impl.SecurityUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {
    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Autowired
    private OAuthSuccessHandler successHandler;

    // configuration of filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // configuration
        System.out.println("🔍 SecurityFilterChain Loaded!");
        return http.authorizeHttpRequests((authorize)->{
                    authorize.requestMatchers("/user/**").authenticated();
                    authorize.anyRequest().permitAll();
                })
                .formLogin(formLogin->{
                    formLogin.loginPage("/login")
                            .loginProcessingUrl("/authenticate")
                            .defaultSuccessUrl("/user/dashboard")
                            .failureUrl("/login?error=true")
                            .usernameParameter("email")
                            .passwordParameter("password");
                })
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logoutForm->{
                    logoutForm.logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout=true");
                })
                .oauth2Login(oauth->{
                    oauth.loginPage("/login");
                    oauth.successHandler(successHandler);
                })
                .build();
    }

    // create a custom authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
