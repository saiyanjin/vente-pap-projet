package com.vente.service;

import com.vente.model.Objet;
import com.vente.repository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffichageObjetService {

    @Autowired
    private ObjetRepository objetRepository;

    public List<Objet> recupererTousLesObjets() {
        // Récupérer tous les objets dans la base de données
        return objetRepository.findAll();
    }
}
