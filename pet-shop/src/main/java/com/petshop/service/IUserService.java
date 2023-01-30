package com.petshop.service;

import java.util.List;


import com.petshop.model.User;

public interface IUserService {

	List<User> getAllUsers();

	User getUserById(long id);

	User createUser(User user);

	User updateUser(User user);

	void deleteUser(long id);

}
