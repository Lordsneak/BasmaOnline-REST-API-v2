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
import com.basmaonline.ws.request.CategoryRequest;

import com.basmaonline.ws.responses.CategoryReponse;
import com.basmaonline.ws.responses.ErrorMessage;

import com.basmaonline.ws.services.CategoryService;
import com.basmaonline.ws.shared.dto.CategoryDto;

@RestController

@RequestMapping("/cat")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CategoryReponse> getCategory(@PathVariable String id) {

		CategoryDto categoryDto = categoryService.getCategoryById(id);

		CategoryReponse categoryReponse = new CategoryReponse();

		BeanUtils.copyProperties(categoryDto, categoryReponse);

		return new ResponseEntity<>(categoryReponse, HttpStatus.OK);

	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<CategoryReponse> getAllCategorys(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		List<CategoryReponse> categoryReponse = new ArrayList<>();

		List<CategoryDto> categorys = categoryService.getCategorys(page, limit);

		for (CategoryDto categoryDto : categorys) {
			
			CategoryReponse user = new CategoryReponse();

			BeanUtils.copyProperties(categoryDto, user);

			categoryReponse.add(user);
		}
		return categoryReponse;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CategoryReponse> createCategory(@RequestBody CategoryRequest categoryRequest)
			throws Exception {

		if (categoryRequest.getName().isEmpty() || categoryRequest.getImage().isEmpty())
			throw new UserException(ErrorMessage.MISSING_REQUIRED_FIELD.getErrorMessage());

		CategoryDto categoryDto = new CategoryDto();

		BeanUtils.copyProperties(categoryRequest, categoryDto);

		CategoryDto createCategory = categoryService.createCategory(categoryDto);

		CategoryReponse categoryReponse = new CategoryReponse();

		BeanUtils.copyProperties(createCategory, categoryReponse);

		return new ResponseEntity<>(categoryReponse, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CategoryReponse> updateCategory(@PathVariable String id,
			@RequestBody CategoryRequest categoryRequest) {

		CategoryDto categoryDto = new CategoryDto();

		BeanUtils.copyProperties(categoryRequest, categoryDto);

		CategoryDto updateCategory = categoryService.updateCategory(id, categoryDto);

		CategoryReponse categoryReponse = new CategoryReponse();

		BeanUtils.copyProperties(updateCategory, categoryReponse);

		return new ResponseEntity<>(categoryReponse, HttpStatus.ACCEPTED);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable String id) {
		categoryService.deleteCategory(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
