package com.vente.controller;

import com.vente.service.AffichageObjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AffichageObjetController {

    @Autowired
    private AffichageObjetService affichageObjetService;

    @GetMapping("/objets")
    public String afficherObjetsEnVente(Model model) {
        // Récupérer tous les objets depuis le service
        model.addAttribute("objets", affichageObjetService.recupererTousLesObjets());
        return "objets"; // Nom de la vue Thymeleaf
    }
}
