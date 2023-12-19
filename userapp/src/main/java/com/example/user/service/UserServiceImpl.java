package com.example.user.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.user.model.UserInfo;
import com.example.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserInfo addUser(UserInfo user) {
		// TODO Auto-generated method stub
		if(user!=null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return user;
		}
		return null;
	}

	@Override
	public boolean loginUser(String username, String password) {
		// TODO Auto-generated method stub
		List<UserInfo> employee = userRepository.validateUser(username, password);
		if(employee.size()>0) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserInfo> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public UserInfo getUserById(int empId) {
		// TODO Auto-generated method stub
		UserInfo employee = userRepository.findById(empId).orElse(null);
		if(employee != null) {
			return employee;
		}
		return null;
	}

}

