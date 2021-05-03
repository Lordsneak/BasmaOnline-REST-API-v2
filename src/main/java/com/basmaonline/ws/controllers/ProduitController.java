package com.basmaonline.ws.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basmaonline.ws.exception.UserException;
import com.basmaonline.ws.request.ProduitRequest;
import com.basmaonline.ws.responses.ErrorMessage;
import com.basmaonline.ws.responses.ProduitReponse;
import com.basmaonline.ws.services.ProduitService;
import com.basmaonline.ws.shared.dto.ProduitDto;

@RestController

@RequestMapping("/p")
public class ProduitController {
	
	@Autowired
	ProduitService produitService;
	
	
	
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProduitReponse> getProduit(@PathVariable String id) {

		ProduitDto produitDto = produitService.getProduitById(id);

		ProduitReponse produitReponse = new ProduitReponse();

		BeanUtils.copyProperties(produitDto, produitReponse);

		return new ResponseEntity<>(produitReponse, HttpStatus.OK);

	}
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<ProduitReponse> getAllProduits(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		List<ProduitReponse> produitReponse = new ArrayList<>();

		List<ProduitDto> produits = produitService.getProduits(page, limit);

		for (ProduitDto produitDto : produits) {
			
			ProduitReponse prod = new ProduitReponse();

			BeanUtils.copyProperties(produitDto, prod);

			produitReponse.add(prod);
		}
		
		return produitReponse;
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProduitReponse> createProduit(@RequestBody ProduitRequest produitRequest)
			throws Exception {

		
		ProduitDto produitDto = new ProduitDto();

		BeanUtils.copyProperties(produitRequest, produitDto);

		ProduitDto createProduit = produitService.createProduit(produitDto);

		ProduitReponse produitReponse = new ProduitReponse();

		BeanUtils.copyProperties(createProduit, produitReponse);

		return new ResponseEntity<>(produitReponse, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProduitReponse> updateproduit(@PathVariable String id,
			@RequestBody ProduitRequest produitRequest) {

		ProduitDto produitDto = new ProduitDto();

		BeanUtils.copyProperties(produitRequest, produitDto);

		ProduitDto updateProduit = produitService.updateProduit(id, produitDto);

		ProduitReponse produitReponse = new ProduitReponse();

		BeanUtils.copyProperties(updateProduit, produitReponse);

		return new ResponseEntity<>(produitReponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteProduit(@PathVariable String id) {
		produitService.deleteProduit(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
