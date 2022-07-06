package com.danchikov.controller;


import com.danchikov.entity.User;
import com.danchikov.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private static final String REGISTRATION_VIEW = "/registration";
    private static final String USER_MODEL = "user";
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(REGISTRATION_VIEW)
    public String registration(Model model) {
        model.addAttribute(USER_MODEL, new User());

        return REGISTRATION_VIEW;
    }

    @PostMapping(REGISTRATION_VIEW)
    public String addUser(@ModelAttribute(USER_MODEL) @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return REGISTRATION_VIEW;
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return REGISTRATION_VIEW;
        }

        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return REGISTRATION_VIEW;
        }

        return "redirect:/";
    }
}
