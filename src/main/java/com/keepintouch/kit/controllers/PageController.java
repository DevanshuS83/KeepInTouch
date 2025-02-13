package com.keepintouch.kit.controllers;

import org.springframework.ui.Model;
import lombok.Locked;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page is working");
        model.addAttribute("Name", "Devmini");
        model.addAttribute("Email", "devmini@gmail.com");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page is working");
        return "about";
    }

    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page is working");
        return "services";
    }

    @RequestMapping("/contact")
    public String contactUsPage(){
        System.out.println("Contact Us page is working");
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage(){
        System.out.println("Login page is working");
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(){
        System.out.println("Signup page is working");
        return "register";
    }


}
