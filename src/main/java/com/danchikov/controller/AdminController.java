package com.danchikov.controller;


import com.danchikov.entity.User;
import com.danchikov.repository.UserRepository;
import com.danchikov.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private List<User> listOfUsers;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        listOfUsers = userRepository.findAll();
        model.addAttribute("users", listOfUsers);
        return "/admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(defaultValue = "") Long userId,
                              @RequestParam(defaultValue = "") String action) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }

        return "redirect:/admin";
    }
}

