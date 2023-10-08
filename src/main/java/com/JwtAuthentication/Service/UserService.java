package com.JwtAuthentication.Service;

import com.JwtAuthentication.Entities.User;
import com.JwtAuthentication.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getUser(){
		return userRepository.findAll();
	}
	public User createUser(User user){
		user.setId(UUID.randomUUID().toString());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
