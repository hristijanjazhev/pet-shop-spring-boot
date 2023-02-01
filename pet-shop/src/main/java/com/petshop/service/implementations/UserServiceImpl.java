package com.petshop.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.User;
import com.petshop.repository.IUserRepository;
import com.petshop.service.IUserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserRepository userRepository;

	public UserServiceImpl(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		List<User> allUsers = this.userRepository.findAll();
		if (allUsers.size() >= 10) {
			throw new ResourceNotFoundException("Cannot create more than 10 users!");
		}
		user.setFirstName(user.getFirstName());
		user.setLastName(user.getLastName());
		user.setEmail(user.getEmail());
		user.setBudget(user.getBudget());
		return this.userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User getUserById(long id) {
		Optional<User> userDb = this.userRepository.findById(id);
		if (userDb.isPresent()) {
			return userDb.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id: !" + id);
		}
	}

	@Override
	public User updateUser(User user) {
		Optional<User> userDb = this.userRepository.findById(user.getId());
		if (userDb.isPresent()) {
			User userUpdate = userDb.get();
			userUpdate.setId(user.getId());
			user.setFirstName(user.getFirstName());
			user.setLastName(user.getLastName());
			user.setEmail(user.getEmail());
			user.setBudget(user.getBudget());
			this.userRepository.save(user);
			return userUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id: !" + user.getId());
		}
	}

	@Override
	public void deleteUser(long id) {
		this.userRepository.deleteById(id);
	}
}
