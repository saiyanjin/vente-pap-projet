package com.vente.controller;

import com.vente.model.Vendeur;
import com.vente.service.VendeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {

    @Autowired
    private VendeurService vendeurService;

    @GetMapping
    public String afficherFormulaire(Model model) {
        model.addAttribute("vendeur", new Vendeur());
        return "inscription";
    }

    @PostMapping
    public String inscrireVendeur(Vendeur vendeur, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inscription";
        }

        try {
            vendeurService.inscrireVendeur(vendeur);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "inscription";
        }
        
        return "redirect:/index";  // Redirection vers l'ajout d'objet
    }


}