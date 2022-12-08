package com.deloite.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.deloite.blog.entities.Category;
import com.deloite.blog.entities.Comment;
import com.deloite.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();

}
