package com.gigin.project.contoller;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gigin.project.model.User;
import com.gigin.project.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userservice;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@GetMapping("/welcome")
	public String welcome() {
		logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

		return "welcome";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody User user)
	{
		Object u = userservice.signup(user);
		if(u!=null) {
			logger.info("signup succeffull");
			return ResponseEntity.status(200).body(u);
		}
		logger.error("signup is not succeffull");
		return ResponseEntity.status(400).body(u);
		
	}
	
	@GetMapping("/login")
	public ResponseEntity<Object> login(@RequestHeader String username,@RequestHeader String password)
	{
		Object u = userservice.login(username,password);
		if(u instanceof User) {
			return ResponseEntity.status(200).body(u);
		}
		return ResponseEntity.status(400).body(u);
	}
	
	@PutMapping("/forgot")
	public ResponseEntity<String> forgot(@RequestHeader String email,@RequestHeader String newpass, @RequestHeader String confpass)
	{
		String s = userservice.forgot(email,newpass,confpass);
		if(s!=null) {
			return ResponseEntity.status(200).body(s);
		}
		return ResponseEntity.status(400).body(s);
	}
	
	@GetMapping("/getuser/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Integer id)
	{
		User u = userservice.getUser(id);
		if(u!=null) {
			return ResponseEntity.status(200).body(u);
		}
		return ResponseEntity.status(400).body(u);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<User>> removeUser(@PathVariable("id") Integer id)
	{
		List<User> u = userservice.removeUser(id);
		if(u!=null) {
			return ResponseEntity.status(200).body(u);
		}
		return ResponseEntity.status(400).body(u);	
	}
	

	@GetMapping("/getall")
	public ResponseEntity<List<User>> getAll()
	{
		List<User> u = userservice.getAll();
		if(u!=null && u.size()>0) {
			return ResponseEntity.status(200).body(u);
		}
		return ResponseEntity.status(400).body(u);	
	}
	
}
