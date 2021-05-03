package com.basmaonline.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basmaonline.ws.Entity.ProduitEntity;


@Repository
public interface ProduitRepository extends JpaRepository<ProduitEntity, Long>{
	
	ProduitEntity findByName(String name);
	ProduitEntity findByIdProduit(String IdProduit);

}
