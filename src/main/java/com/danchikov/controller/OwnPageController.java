package com.danchikov.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OwnPageController {
    private static final String OWN_PAGE_VIEW = "/ownPage";
    private static final String NICK_TO_SHOW = "nick";

    @GetMapping(OWN_PAGE_VIEW)
    public String getProfile(Model model) {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        model.addAttribute(NICK_TO_SHOW,
                SecurityContextHolder.getContext().getAuthentication().getName());
        return OWN_PAGE_VIEW;
    }
}
