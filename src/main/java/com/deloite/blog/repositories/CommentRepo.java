package com.deloite.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloite.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	

}
