package com.basmaonline.ws.services;

import java.util.List;


import com.basmaonline.ws.shared.dto.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto category);
	List<CategoryDto> getCategorys(int page, int limit);
	CategoryDto getCategory(String name);
	CategoryDto getCategoryById(String categoryId);
	CategoryDto updateCategory(String categoryId, CategoryDto category);
	void deleteCategory(String categoryId);
	
}