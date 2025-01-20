package com.vente.repository;

import com.vente.model.Objet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ObjetRepository extends JpaRepository<Objet, Long> {

    @Query("SELECT o FROM Objet o WHERE o.vendu = false AND o.descriptif LIKE %:keyword%")
    List<Objet> rechercherParMotCle(@Param("keyword") String keyword);
}
