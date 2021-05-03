package com.basmaonline.ws.shared.dto;

import java.io.Serializable;

public class ProduitDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8262226890326754660L;
	
	private long id;
	private String idProduit;
	private double prixProduit;
	private int qtStockProduit;
	private double tvaProduit;
	private double remiseProduit;
	private String name;
	private String imageProduit;
	private String descriptionProduit;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(String idProduit) {
		this.idProduit = idProduit;
	}
	public double getPrixProduit() {
		return prixProduit;
	}
	public void setPrixProduit(double prixProduit) {
		this.prixProduit = prixProduit;
	}
	public int getQtStockProduit() {
		return qtStockProduit;
	}
	public void setQtStockProduit(int qtStockProduit) {
		this.qtStockProduit = qtStockProduit;
	}
	public double getTvaProduit() {
		return tvaProduit;
	}
	public void setTvaProduit(double tvaProduit) {
		this.tvaProduit = tvaProduit;
	}
	public double getRemiseProduit() {
		return remiseProduit;
	}
	public void setRemiseProduit(double remiseProduit) {
		this.remiseProduit = remiseProduit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageProduit() {
		return imageProduit;
	}
	public void setImageProduit(String imageProduit) {
		this.imageProduit = imageProduit;
	}
	public String getDescriptionProduit() {
		return descriptionProduit;
	}
	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}
	
	
}
