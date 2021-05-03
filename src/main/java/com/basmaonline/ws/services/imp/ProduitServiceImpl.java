package com.basmaonline.ws.services.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.basmaonline.ws.Entity.ProduitEntity;
import com.basmaonline.ws.repository.ProduitRepository;
import com.basmaonline.ws.services.ProduitService;
import com.basmaonline.ws.shared.Utils;
import com.basmaonline.ws.shared.dto.ProduitDto;


@Service
public class ProduitServiceImpl implements ProduitService{

	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	Utils util;
	
	@Override
	public ProduitDto createProduit(ProduitDto produit) {
		
		ProduitEntity checkprod = produitRepository.findByName(produit.getName());
		if(checkprod != null) throw new RuntimeException("Produit Already Exists");
		
		ProduitEntity prod = new ProduitEntity();
		
		BeanUtils.copyProperties(produit, prod);
		
		prod.setIdProduit(util.generateUserId(6));


		ProduitEntity newProduit = produitRepository.save(prod);
		
		ProduitDto produitDto = new ProduitDto();
		
		BeanUtils.copyProperties(newProduit, produitDto);
		
		return produitDto;
	}

	@Override
	public List<ProduitDto> getProduits(int page, int limit) {
		if(page > 0) page = page -1;
		
		List<ProduitDto> produitsDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<ProduitEntity> produitPage =	produitRepository.findAll(pageableRequest);
		
		List<ProduitEntity> produits = produitPage.getContent();
		
		for(ProduitEntity produitEntity: produits) {
			ProduitDto produit = new ProduitDto();
			
			BeanUtils.copyProperties(produitEntity, produit);
			
			produitsDto.add(produit);
		}
		
	
		return produitsDto;
	}

	@Override
	public ProduitDto getProduit(String name) {
		ProduitEntity produitEntity = produitRepository.findByName(name);
		
		if (produitEntity == null ) throw new UsernameNotFoundException(name);
		
		ProduitDto produitDto = new ProduitDto();
		
		BeanUtils.copyProperties(produitEntity, produitDto);
		
		return produitDto;
	}

	@Override
	public ProduitDto getProduitById(String IdProduit) {
		
		ProduitEntity produitEntity = produitRepository.findByIdProduit(IdProduit);
		
		if (produitEntity == null ) throw new RuntimeException("Error !");
		
		ProduitDto produitDto = new ProduitDto();
		
		BeanUtils.copyProperties(produitEntity, produitDto);
		
		return produitDto;
	}

	@Override
	public ProduitDto updateProduit(String IdProduit, ProduitDto produit) {
		ProduitEntity produitEntity = produitRepository.findByIdProduit(IdProduit);
		
		if (produitEntity == null ) throw new RuntimeException("Error !");
		
		produitEntity.setName(produit.getName());
		produitEntity.setDescriptionProduit(produit.getDescriptionProduit());
		produitEntity.setPrixProduit(produit.getPrixProduit());
		produitEntity.setImageProduit(produit.getImageProduit());
		produitEntity.setQtStockProduit(produit.getQtStockProduit());
		produitEntity.setRemiseProduit(produit.getRemiseProduit());
		produitEntity.setTvaProduit(produit.getTvaProduit());
		
		ProduitEntity produitUpdate = produitRepository.save(produitEntity);
		
		ProduitDto produits = new ProduitDto();
		
		BeanUtils.copyProperties(produitUpdate, produits);
		
		return produit;
	}

	@Override
	public void deleteProduit(String IdProduit) {
	
		ProduitEntity produitEntity = produitRepository.findByIdProduit(IdProduit);
		if (produitEntity == null ) throw new RuntimeException("Error ! Id Not found");
		
		produitRepository.delete(produitEntity);
		
	}

}
