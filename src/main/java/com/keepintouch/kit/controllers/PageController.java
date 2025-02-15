package com.keepintouch.kit.controllers;

import com.keepintouch.kit.forms.UserForm;
import com.keepintouch.kit.helpers.Message;
import com.keepintouch.kit.helpers.MessageType;
import com.keepintouch.kit.models.User;
import com.keepintouch.kit.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PageController {
    @Autowired
    private UserService userService;

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
    public String signupPage(Model model){
        System.out.println("Signup page is working");
        UserForm userForm = new UserForm();
        // default data can also be added
        model.addAttribute("userForm", userForm);
        return "register";
    }

    //process register request
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        // validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(String.valueOf(userForm.getPhoneNumber()));
        user.setProfilePic("/images/defaultProfilePic.png");

        Optional<User> savedUser = userService.saveUser(user);
        Message message = new Message();
        if(savedUser.isPresent()) {
            message.setContent("User registered successfully");
            message.setType(MessageType.green);

        } else {
            message.setContent("Something went wrong");
            message.setType(MessageType.red);
        }
        session.setAttribute("message", message);
       
       
        return "redirect:/signup";
    }


}
