package com.petshop.service.implementations;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.HistoryLog;
import com.petshop.model.Pet;
import com.petshop.model.User;
import com.petshop.repository.IHistoryLogRepository;
import com.petshop.repository.IPetRepository;
import com.petshop.repository.IUserRepository;
import com.petshop.service.IPurchasePetService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchasePetImpl implements IPurchasePetService {
	@Autowired
	private IPetRepository petRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IHistoryLogRepository historyLogRepository;

	public PurchasePetImpl(IPetRepository petRepository, IUserRepository userRepository,
			IHistoryLogRepository historyLogRepository) {
		this.petRepository = petRepository;
		this.userRepository = userRepository;
		this.historyLogRepository = historyLogRepository;
	}

	@Override
	public Object purchasePet(Long petId, Long userId) {

		Pet pets = this.petRepository.findById(petId).orElse(null);
		User user = this.userRepository.findById(userId).orElse(null);
		if (pets == null || user == null) {
			return new ResourceNotFoundException("Pet or User not found!");
		}
		if (pets.getOwner() != null) {
			return new ResourceNotFoundException("Pet already has an owner");
		}
		if (user.getBudget() < pets.getPrice()) {
			return new ResourceNotFoundException("User does not have enough budget");
		}
		HistoryLog history = new HistoryLog();
		history.setPet(pets);
		history.setUser(user);
		history.setDate(LocalDate.now());

		this.historyLogRepository.save(history);
		pets.setOwner(user);
		user.setBudget(user.getBudget() - pets.getPrice());
		this.petRepository.save(pets);
		this.userRepository.save(user);

		if (pets.getType().equals("Cat")) {
			return "Meow, cat " + pets.getName() + " has owner " + user.getFirstName() + " " + user.getLastName();
		} else {
			return "Woof, dog " + pets.getName() + " has owner " + user.getFirstName() + " " + user.getLastName();
		}
	}
}
