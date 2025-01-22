package com.vente.controller;

import com.vente.service.MesObjetsService;
import com.vente.model.Objet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MesObjetsController {

    @Autowired
    private MesObjetsService mesObjetsService;

    @GetMapping("/mes-objets")
    public String afficherMesObjets(@AuthenticationPrincipal User user, Model model) {
        List<Objet> mesObjets = mesObjetsService.recupererObjetsParVendeur(user.getUsername());
        model.addAttribute("objets", mesObjets);
        return "mes-objets";
    }
    
    @PostMapping("/mes-objets")
    public String vendreObjet(@RequestParam("id") Long id, @AuthenticationPrincipal User user) {
        String username = user.getUsername();
        mesObjetsService.vendreObjet(id, username);
        return "redirect:/mes-objets"; 
    }
}
