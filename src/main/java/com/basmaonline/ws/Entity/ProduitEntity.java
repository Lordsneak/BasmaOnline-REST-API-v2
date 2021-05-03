package com.basmaonline.ws.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity(name = "produits")

public class ProduitEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6618828680821466686L;

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private String idProduit;
	@Column(nullable = false)
	private double prixProduit;
	@Column(nullable = false)
	private int qtStockProduit;
	@Column(nullable = false)
	private double tvaProduit;
	@Column(nullable = false)
	private double remiseProduit;
	@Column(nullable = false)
	private String imageProduit;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String descriptionProduit;
	
	@ManyToOne
	private CategoryEntity category;
	
	
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
	public String getImageProduit() {
		return imageProduit;
	}
	public void setImageProduit(String imageProduit) {
		this.imageProduit = imageProduit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescriptionProduit() {
		return descriptionProduit;
	}
	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}
	public CategoryEntity getCategory() {
		return category;
	}
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	
	
	


}
