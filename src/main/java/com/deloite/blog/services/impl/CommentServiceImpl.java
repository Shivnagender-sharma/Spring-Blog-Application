package com.deloite.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloite.blog.entities.Comment;
import com.deloite.blog.entities.Post;
import com.deloite.blog.exceptions.ResourceNotFoundException;
import com.deloite.blog.payloads.CommentDto;
import com.deloite.blog.repositories.CommentRepo;
import com.deloite.blog.repositories.PostRepo;
import com.deloite.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		Comment comment = this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment,CommentDto.class );
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment com = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Comment id ", commentId));
		this.commentRepo.delete(com);
	}
	

}
