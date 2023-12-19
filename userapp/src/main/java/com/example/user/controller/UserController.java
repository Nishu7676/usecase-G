package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.model.UserInfo;
import com.example.user.service.UserService;

@RestController
@RequestMapping("userMarketdb")
public class UserController 
{

	@Autowired
	private UserService us;
	
	
	@GetMapping("/admin/getAllUsers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> getAllUsers()
	{
		List<UserInfo> userList = us.getAllUsers();
		
		if(userList !=null)
		{
			return new ResponseEntity<List<UserInfo>>(userList, HttpStatus.OK);
		}
		return new ResponseEntity<String>("list is empty", HttpStatus.OK);
		
	}
	
	@GetMapping("/user/info/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<?> getEmployeeById(@PathVariable("userId") int userId) {
		UserInfo user = us.getUserById(userId);
		
		if(user != null) {
			return new ResponseEntity<UserInfo>(user, HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<String>("Employee not found", HttpStatus.NOT_FOUND);
	}
}

