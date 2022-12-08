package com.deloite.blog;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.deloite.blog.controllers.UserController;
import com.deloite.blog.entities.User;
import com.deloite.blog.repositories.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private UserRepo userRepo;

	@InjectMocks
	private UserController userController;

	User Record_1 = new User( "Manish",  "manish@gmail.com",  "mnsh@9876",  "I am Web Developer");

}
