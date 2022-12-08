package com.deloite.blog.services;

import java.util.List;

import com.deloite.blog.payloads.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//uodate
	CategoryDto updateCategory(CategoryDto categoryDto , Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategory(Integer categoryId);
	
	//getAll
	List<CategoryDto> getCategories();

}



//concept of interface is used for loose coupling so that whenever we require we can change its implementation 
