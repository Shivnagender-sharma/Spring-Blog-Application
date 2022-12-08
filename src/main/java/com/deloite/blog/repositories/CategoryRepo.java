package com.deloite.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloite.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
