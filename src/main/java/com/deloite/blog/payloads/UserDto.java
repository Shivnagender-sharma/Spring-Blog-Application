package com.deloite.blog.payloads;



import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.deloite.blog.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	
	private int id;
	
	//Request validation
	@NotEmpty@Size(min = 4 , message = "Username must be of min 4 characters ")
	private String name ;
	
	@Email(message="Email Address is not valid !!")
	private String email;
	
	@NotEmpty @Size(min=3 , max =10, message = "Password must be min of 3 chars and max of 10 chars !!")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
