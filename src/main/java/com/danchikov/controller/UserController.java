package com.danchikov.controller;

import com.danchikov.entity.User;
import com.danchikov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String showRegForm(Model model, User user){
        model.addAttribute(new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "registration";
        }
        userService.saveUser(user);
        return "mainPage";
    }


}
