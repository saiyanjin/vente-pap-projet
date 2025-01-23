package com.vente.service;

import com.vente.repository.ObjetRepository;
import org.springframework.stereotype.Service;

@Service
public class ChiffreAffairesService {

    private final ObjetRepository objetRepository;

    public ChiffreAffairesService(ObjetRepository objetRepository) {
        this.objetRepository = objetRepository;
    }

    public double calculerChiffreAffaires() {
        double totalVentes = objetRepository.sumPrixByVenduTrue().orElse(0.0);
        
        // Appliquer une commission de 10 %
        return totalVentes * 0.10;
    }
}
