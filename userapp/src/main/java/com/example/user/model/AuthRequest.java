package com.example.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//@Entity
public class AuthRequest {

	//@Column
	private String username;
	
	//@Column
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public AuthRequest() {
		super();
	}
}