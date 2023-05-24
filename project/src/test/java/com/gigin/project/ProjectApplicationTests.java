package com.gigin.project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.gigin.project.model.User;
import com.gigin.project.repo.UserRepository;
import com.gigin.project.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectApplicationTests {
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;

	@Test
	public void getAllTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(127,"krishna","krish777","krish@gmail.com",9755788745l),
						new User(128,"radha","radhe777","radhe@gmail.com",9855788345l)).collect(Collectors.toList()));
		assertEquals(2,service.getAll().size());				
	}
	
	@Test
	public void signupTest() {
		User user = new User(128,"radha","radhe777","radhe@gmail.com",9855788345l);
		when(repository.save(user)).thenReturn((user));		
		assertEquals(user,service.signup(user));				
	}
	
	@Test
	public void removeUserTest() {
		int id =128;
		User user = new User(128,"radha","radhe777","radhe@gmail.com",9855788345l);
		service.removeUser(id);
			verify(repository,times(1)).deleteById(id);
	}
}
