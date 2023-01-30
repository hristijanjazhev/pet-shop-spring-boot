package com.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.model.User;
import com.petshop.service.IUserService;

@RestController
@RequestMapping("/all")
public class UserController {
	@Autowired
	private IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok().body(this.userService.getAllUsers());
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Object> getUsersById(@PathVariable long id) {
		return ResponseEntity.ok().body(this.userService.getUserById(id));
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUsers(@PathVariable long id, @RequestBody User user) {
		user.setId(id);
		return ResponseEntity.ok().body(this.userService.updateUser(user));
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok().body(this.userService.createUser(user));
	}

	@DeleteMapping("/user/{id}")
	public HttpStatus removeUser(@PathVariable long id) {
		this.userService.deleteUser(id);
		return HttpStatus.OK;
	}
}
