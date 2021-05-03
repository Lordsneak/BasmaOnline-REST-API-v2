package com.basmaonline.ws.services;

import java.util.List;

import com.basmaonline.ws.shared.dto.ProduitDto;


public interface ProduitService {
	
	ProduitDto createProduit(ProduitDto produit);
	List<ProduitDto> getProduits(int page, int limit);
	ProduitDto getProduit(String name);
	ProduitDto getProduitById(String idProduit);
	ProduitDto updateProduit(String idProduit, ProduitDto produit);
	void deleteProduit(String idProduit);

}
