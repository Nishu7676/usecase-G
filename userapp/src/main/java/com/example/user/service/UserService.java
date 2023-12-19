package com.example.user.service;

import java.util.List;
import com.example.user.model.UserInfo;


public interface UserService {
	public UserInfo addUser(UserInfo user);
	
	public boolean loginUser(String username, String password);
	
	public List<UserInfo> getAllUsers();
	
	public UserInfo getUserById(int userId);
}
