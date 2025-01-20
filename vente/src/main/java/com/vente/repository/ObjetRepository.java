package com.vente.repository;

import com.vente.model.Objet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjetRepository extends JpaRepository<Objet, Long> {
	
}