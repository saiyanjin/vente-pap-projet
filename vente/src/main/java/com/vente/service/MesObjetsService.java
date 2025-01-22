package com.vente.service;

import com.vente.model.Objet;
import com.vente.repository.ObjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesObjetsService {

    @Autowired
    private ObjetRepository objetRepository;

    public List<Objet> recupererObjetsParVendeur(String username) {
        return objetRepository.findByVendeurLogin(username);
    }
    
    public void vendreObjet(Long id, String username) {
        Objet objet = objetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Objet non trouvé avec l'ID : " + id));

        // Vérifier que l'objet appartient au vendeur connecté
        if (!objet.getVendeur().getLogin().equals(username)) {
            throw new SecurityException("Vous n'êtes pas autorisé à vendre cet objet.");
        }

        // Marquer l'objet comme vendu
        objet.setVendu(true);
        objetRepository.save(objet);
    }
}
