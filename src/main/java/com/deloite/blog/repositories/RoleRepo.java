package com.deloite.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deloite.blog.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
