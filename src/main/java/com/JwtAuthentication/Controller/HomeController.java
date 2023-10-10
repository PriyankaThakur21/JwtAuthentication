package com.JwtAuthentication.Controller;

import com.JwtAuthentication.Entities.User;
import com.JwtAuthentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userservice;

	@GetMapping("/test")
	public String home(){
		return "Welcome to my Website!!";
	}
	@GetMapping("/users")
	public List<User> user()
	{
		return this.userservice.getUser();
	}

	@GetMapping("/currentUser")
	public String getUser(Principal principal){
		return principal.getName();
	}

}
