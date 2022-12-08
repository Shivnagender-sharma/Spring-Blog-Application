package com.deloite.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.deloite.blog.entities.Category;
import com.deloite.blog.entities.Post;
import com.deloite.blog.entities.User;
import com.deloite.blog.exceptions.ResourceNotFoundException;
import com.deloite.blog.payloads.PostDto;
import com.deloite.blog.payloads.PostResponse;
import com.deloite.blog.repositories.CategoryRepo;
import com.deloite.blog.repositories.PostRepo;
import com.deloite.blog.repositories.UserRepo;
import com.deloite.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo; 
	
	
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId , Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		 post.setImageName("default.png");
		 post.setAddedDate(new Date());
		 post.setUser(user);
		 post.setCategory(category);
		 
		 Post newPost = this.postRepo.save(post);
		 
		 return this.modelMapper.map(newPost, PostDto.class);
		 
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost  = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
		
		
		
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post id", postId));
		this.postRepo.delete(post);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber , Integer pageSize,String sortBy, String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort= Sort.by(sortBy).ascending();
		}else
		{
			sort= Sort.by(sortBy).descending();
		}
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		// creating default constructor of PostREsponse
		PostResponse postReponse = new PostResponse();
		
		postReponse.setContent(postDtos);
		postReponse.setPageNumber(pagePost.getNumber());
		postReponse.setPageSize(pagePost.getSize());
		postReponse.setTotalElements(pagePost.getTotalElements());
		postReponse.setTotalPages(pagePost.getTotalPages());
		postReponse.setLastPage(pagePost.isLast());
		
		return postReponse;
	}

	@Override
	public PostDto getPostsById(Integer postId) {
		
		Post post  = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
         
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId)); 
		 List<Post> posts = this.postRepo.findByCategory(cat);
		 
		  List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		  return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		 
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
		 List<Post> posts = this.postRepo.findByUser(user);
		 
		  List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		    
		return postDtos ;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
