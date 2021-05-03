package com.basmaonline.ws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basmaonline.ws.Entity.CategoryEntity;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	CategoryEntity findByName(String name);
	CategoryEntity findByCategoryId(String categoryId);
}
