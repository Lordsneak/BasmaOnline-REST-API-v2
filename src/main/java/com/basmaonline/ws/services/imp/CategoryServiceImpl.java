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

import com.basmaonline.ws.Entity.CategoryEntity;
import com.basmaonline.ws.repository.CategoryRepository;
import com.basmaonline.ws.services.CategoryService;
import com.basmaonline.ws.shared.Utils;
import com.basmaonline.ws.shared.dto.CategoryDto;


@Service
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	Utils util;
	
	@Override
	public CategoryDto createCategory(CategoryDto category) {
		
		CategoryEntity checkcat = categoryRepository.findByName(category.getName());
		if(checkcat != null) throw new RuntimeException("Category Already Exists");
		
		CategoryEntity categoryEntity = new CategoryEntity();
		
		BeanUtils.copyProperties(category, categoryEntity);
		
		categoryEntity.setCategoryId(util.generateUserId(6));


		CategoryEntity newCategory = categoryRepository.save(categoryEntity);
		
		CategoryDto categoryDto = new CategoryDto();
		
		BeanUtils.copyProperties(newCategory, categoryDto);
		
		return categoryDto;
		

		
	}

	@Override
	public List<CategoryDto> getCategorys(int page, int limit) {
		if(page > 0) page = page -1;
		
		List<CategoryDto> categorysDto = new ArrayList<>();
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		
		Page<CategoryEntity> categoryPage =	categoryRepository.findAll(pageableRequest);
		
		List<CategoryEntity> categorys = categoryPage.getContent();
		
		for(CategoryEntity categoryEntity: categorys) {
			CategoryDto category = new CategoryDto();
			
			BeanUtils.copyProperties(categoryEntity, category);
			
			categorysDto.add(category);
		}
		
	
		return categorysDto;
	}
	

	@Override
	public CategoryDto getCategory(String name) {
		
		CategoryEntity categoryEntity = categoryRepository.findByName(name);
		
		if (categoryEntity == null ) throw new UsernameNotFoundException(name);
		
		CategoryDto categoryDto = new CategoryDto();
		
		BeanUtils.copyProperties(categoryEntity, categoryDto);
		
		return categoryDto;
	}

	@Override
	public CategoryDto getCategoryById(String categoryId) {
		
		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		
		if (categoryEntity == null ) throw new RuntimeException("Error !");
		
		CategoryDto categoryDto = new CategoryDto();
		
		BeanUtils.copyProperties(categoryEntity, categoryDto);
		
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {

		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		
		if (categoryEntity == null ) throw new RuntimeException("Error !");
		
		categoryEntity.setName(categoryDto.getName());
		categoryEntity.setImage(categoryDto.getImage());
		
		CategoryEntity categoryUpdate = categoryRepository.save(categoryEntity);
		
		CategoryDto category = new CategoryDto();
		
		BeanUtils.copyProperties(categoryUpdate, category);
		
		return category;
		
	}

	@Override
	public void deleteCategory(String categoryId) {
		
		CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId);
		if (categoryEntity == null ) throw new RuntimeException("Error ! Id Not found");
		
		categoryRepository.delete(categoryEntity);
	}

}
