package com.vente.controller;

import com.vente.model.Objet;
import com.vente.model.Vendeur;
import com.vente.service.ObjetService;
import com.vente.service.VendeurService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ObjetController {

    @Autowired
    private ObjetService objetService;
    
    @Autowired
    private VendeurService vendeurService;
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/ajouterObjet")
    public String ajouterObjet(@ModelAttribute("objet") Objet objet) {
        // Récupérer l'utilisateur authentifié (c'est un Vendeur)
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();

        // Trouver le vendeur dans la base à partir du login
        Vendeur vendeur = vendeurService.trouverParLogin(username);

        // Associer l'objet au vendeur
        objet.setVendeur(vendeur);

        // Sauvegarder l'objet
        objetService.enregistrerObjet(objet);

        return "redirect:/index";
    }

    @GetMapping("/ajouterObjet")
    public String afficherFormulaire(Model model) {
        model.addAttribute("objet", new Objet());
        return "ajouterObjet";
    }
}
