package com.danchikov.controller;

import com.danchikov.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class OwnPageController {
    @GetMapping("ownPage")
    public String showList(@ModelAttribute("username") User user){
        System.out.println(user.getUsername());
        return "ownPage";
    }
}
