package com.nitinagrawal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nitinagrawal.entities.User;
import com.nitinagrawal.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public List<User> retrieveUsers() {
		return usersRepository.findAll(Sort.by("userName"));
	}

	@Override
	public User getUser(String userName) {
		return usersRepository.findById(userName).orElse(new User());
	}

	@Override
	public void saveUser(User user) {
		usersRepository.saveAndFlush(user);
	}

	@Override
	public void deleteUser(String userName) {
		usersRepository.deleteById(userName);
	}

	@Override
	public void updateUser(User user) {
		if(usersRepository.existsById(user.getUserName()))
			usersRepository.saveAndFlush(user);
	}

}
