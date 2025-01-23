package com.vente.controller;

import com.vente.service.ChiffreAffairesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChiffreAffairesController {

    private final ChiffreAffairesService chiffreAffairesService;

    public ChiffreAffairesController(ChiffreAffairesService chiffreAffairesService) {
        this.chiffreAffairesService = chiffreAffairesService;
    }

    @GetMapping("/chiffre-affaires")
    public String afficherChiffreAffaires(Model model) {
        // Calcul du chiffre d'affaires total
        double chiffreAffaires = chiffreAffairesService.calculerChiffreAffaires();
        model.addAttribute("chiffreAffaires", chiffreAffaires);
        return "chiffre-affaires";
    }
}
