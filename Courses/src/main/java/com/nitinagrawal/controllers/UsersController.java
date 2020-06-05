package com.nitinagrawal.controllers;

import java.util.List;
import static java.util.stream.Collectors.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitinagrawal.entities.User;
import com.nitinagrawal.services.UserService;

@RestController
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<String> getUsers() {
		return userService.retrieveUsers()
						  .stream()
				          .map(user -> user.getUserName())
				          .collect(toList());
	}
	
	@GetMapping("/users/{userName}")
	public User getUser(@PathVariable String userName) {
		User user = userService.getUser(userName);
		user.setPassword("*****");
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<List<String>> addUser(@RequestBody List<User> users) {
		users.forEach(user -> userService.saveUser(user));
		List<String> userNames = users.stream()
									  .map(user -> user.getUserName())
									  .map(name -> userService.getUser(name))
									  .map(savedUser -> savedUser.getUserName())
						              .collect(toList());
		return new ResponseEntity<>(userNames, HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<List<String>> updateUser(@RequestBody List<User> users) {
		users.forEach(user -> userService.saveUser(user));
		List<String> userNames = users.stream()
									  .map(user -> user.getUserName())
									  .map(name -> userService.getUser(name))
									  .map(savedUser -> savedUser.getUserName())
						              .collect(toList());
		return new ResponseEntity<>(userNames, HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteUser(String userName) {
		userService.deleteUser(userName);
		return new ResponseEntity<>("Deleted the User.", HttpStatus.OK);
	}

}
