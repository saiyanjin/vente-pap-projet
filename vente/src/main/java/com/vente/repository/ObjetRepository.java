package com.vente.repository;

import com.vente.model.Objet;
import com.vente.model.Vendeur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ObjetRepository extends JpaRepository<Objet, Long> {

    @Query("SELECT o FROM Objet o WHERE o.vendu = false AND o.descriptif LIKE %:keyword%")
    List<Objet> rechercherParMotCle(@Param("keyword") String keyword);
    
    List<Objet> findByVendeurLogin(String login);
    
    List<Objet> findByVendeurAndVenduFalse(@Param("vendeur") Vendeur vendeur);
    
    @Query("SELECT SUM(o.prix) FROM Objet o WHERE o.vendu = true")
    Optional<Double> sumPrixByVenduTrue();

}
