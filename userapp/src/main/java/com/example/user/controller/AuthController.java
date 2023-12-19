package com.example.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.UserInfo;
import com.example.user.service.UserService;
import com.example.user.service.JwtService;


@RestController
@RequestMapping("/auth/v1.0/user")
public class AuthController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
	@PostMapping("/add/user")
	public ResponseEntity<?> addUser(@RequestBody UserInfo user)
	{
		if(userService.addUser(user)!=null)
		{
			return new ResponseEntity<UserInfo>(user, HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Employee not added", HttpStatus.CONFLICT);
	}
	
	
	@PostMapping("/login")
	public String Login(@RequestBody UserInfo user)
	{
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}else {
			throw new UsernameNotFoundException("Invalid User");
		}
		
	}
}

