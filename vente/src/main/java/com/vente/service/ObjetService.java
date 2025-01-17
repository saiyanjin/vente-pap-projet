package com.vente.service;

import com.vente.model.Objet;
import com.vente.repository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjetService {

    @Autowired
    private ObjetRepository objetRepository;

    public Objet enregistrerObjet(Objet objet) {
        return objetRepository.save(objet);
    }
}