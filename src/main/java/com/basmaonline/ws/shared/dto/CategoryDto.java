package com.basmaonline.ws.shared.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable{

	private static final long serialVersionUID = 8025781982957066994L;
	
	private long id;
	private String categoryId;
	private String name;
	private String image;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

	
	
	

}
