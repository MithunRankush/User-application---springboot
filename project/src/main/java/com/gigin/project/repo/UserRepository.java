package com.gigin.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gigin.project.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
	@Query(value = "SELECT * FROM User u WHERE username = :username", nativeQuery = true)
    public User getUserByUsername(String username);
	
	@Query(value = "SELECT * FROM User u WHERE email = :email", nativeQuery = true)
    public User getUserByEmail(String email);
}
