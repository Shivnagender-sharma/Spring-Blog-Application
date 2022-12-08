package com.deloite.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloite.blog.payloads.ApiResponse;
import com.deloite.blog.payloads.CategoryDto;
import com.deloite.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	
	@Autowired
	private CategoryService categoryService;
	
	//create
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto){
		
	   CategoryDto createCategory	= this.categoryService.createCategory(categoryDto);
	   return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
		
	   CategoryDto updatedCategory	= this.categoryService.updateCategory(categoryDto,catId);
	   return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
		 this.categoryService.deleteCategory(catId);
	  return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted sucessfully!!", true), HttpStatus.OK);
	}
	
	//Get
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
		
		CategoryDto categoryDto = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK );
		
	}
		
	
	
	//Get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
logger.info("Get All Users");
		
		logger.info("ResponseEntity=" + this.categoryService.getCategories());
		
		 List<CategoryDto> categories = this.categoryService.getCategories();
		 return ResponseEntity.ok(categories);
	}
	
	

}
