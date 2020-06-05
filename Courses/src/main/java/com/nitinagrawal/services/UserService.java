package com.nitinagrawal.services;

import java.util.List;

import com.nitinagrawal.entities.User;

public interface UserService {

	public List<User> retrieveUsers();
	public User getUser(String userName);
	public void saveUser(User user);
	public void deleteUser(String userName);
	public void updateUser(User user);
}
