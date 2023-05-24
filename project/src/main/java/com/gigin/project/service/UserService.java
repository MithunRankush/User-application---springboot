package com.gigin.project.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gigin.project.exception.PasswordInvalidException;
import com.gigin.project.exception.UserAlreadyExistsException;
import com.gigin.project.exception.UserNotFoundException;
import com.gigin.project.model.User;
import com.gigin.project.repo.UserRepository;


@Service
public class UserService {
	
	private static Logger logger= LogManager.getLogger(UserService.class);
	@Autowired
	private UserRepository userepo;

	public Object signup(User user) {
		User u = userepo.getUserByUsername(user.getUsername());
		try {
			if(u!=null) {
				throw new UserAlreadyExistsException();
			}else {
				return userepo.save(user);
			}
		}catch(UserAlreadyExistsException e) {
			logger.error("signup is not succeffull bcz user already exits");
			return e.getMessage();
		}
	}

	public Object login(String username, String password) {
		User u = userepo.getUserByUsername(username);
		if(u!=null) {
			if(u.getPassword().equals(password)) {
				return u;
			}else {
				try {
					throw new PasswordInvalidException();
				}catch(PasswordInvalidException e) {
					return e.getMessage();
				}
			}
		}else {
			try {
				throw new UserNotFoundException();
			}catch(UserNotFoundException e) {
				return e.getMessage();
			}
		}
		
	}

	public String forgot(String email, String newpass, String confpass) {
		User u = userepo.getUserByEmail(email);
		if(u!=null) {
			if(newpass.equals(confpass)) {
				u.setPassword(newpass);
				userepo.save(u);
				return "Password updated successfully";
			}
		}else {
			try {
				throw new UserNotFoundException();
			}catch(UserNotFoundException e) {
				return e.getMessage();
			}
		}
		return null;
	}

	public User getUser(Integer id) {
		return userepo.findById(id).get();
	}

	public List<User> removeUser(Integer id) {
		if(userepo.existsById(id)) {
			userepo.deleteById(id);
			return getAll();
		}
		return null;
	}

	public List<User> getAll() {
		return userepo.findAll();
	}

}
