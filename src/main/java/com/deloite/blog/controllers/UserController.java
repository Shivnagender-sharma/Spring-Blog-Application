package com.deloite.blog.controllers;


import java.util.List;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloite.blog.payloads.ApiResponse;
import com.deloite.blog.payloads.UserDto;
import com.deloite.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	//Error<Warn<Info<Debug<Trace
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService ;
	
	//Post - create user
	//In oder to enable validation we use @Vaild annotation
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	
	// PUT - update user
	 //path uri variable
	
	@PutMapping("/{userId}") 
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId){
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	
	//ADMIN
	//DELETE - delete user
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
	
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		
		 this.userService.deleteUser(userId);
		// return  new ResponseEntity(Map.of("message" , "User deleted Successfully"),HttpStatus.OK);
		 
		 return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Sucessfully", true), HttpStatus.OK);
		 
		
	}
	
	
	//GET - user get
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUser(){
		
		
		logger.info("Get All Users" + this.userService.getAllUsers());
		
		
		
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId ){
		
		logger.info(" Getting User for userId = " + userId);
		logger.info("ResponseEntity =" + this.userService.getUserById(userId) );
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	
}
