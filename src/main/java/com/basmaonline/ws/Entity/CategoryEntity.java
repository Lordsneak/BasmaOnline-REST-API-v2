package com.basmaonline.ws.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="category")

public class CategoryEntity implements Serializable{
	
	private static final long serialVersionUID = 667952731272528878L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private String categoryId;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	
	@Column(nullable = false)
	private String image;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	
	

}
