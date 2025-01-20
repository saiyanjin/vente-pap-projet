package com.vente.controller;

import com.vente.service.AffichageObjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AffichageObjetController {

    @Autowired
    private AffichageObjetService affichageObjetService;

    @GetMapping("/objets")
    public String rechercherObjets(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        // Si aucun mot-clé n'est fourni, affiche tous les objets non vendus
        if (keyword == null || keyword.isEmpty()) {
            keyword = "";
        }

        // Récupérer les objets correspondant au mot-clé
        model.addAttribute("objets", affichageObjetService.rechercherObjetsParMotCle(keyword));
        model.addAttribute("keyword", keyword);
        return "objets";
    }
}
