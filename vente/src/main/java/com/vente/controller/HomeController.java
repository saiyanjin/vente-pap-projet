package com.vente.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.User;

@Controller
public class HomeController {

    // Page d'accueil
    @GetMapping("/")
    public String afficherAccueil(@AuthenticationPrincipal User user) {
        return "index";
    }
}