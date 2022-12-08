package com.deloite.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloite.blog.entities.Category;
import com.deloite.blog.entities.Post;
import com.deloite.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
   
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
}
