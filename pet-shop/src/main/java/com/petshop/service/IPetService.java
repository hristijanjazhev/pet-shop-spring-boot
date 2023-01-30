package com.petshop.service;

import java.util.List;

import com.petshop.model.Pet;

public interface IPetService {
	List<Pet> getAllPets();

	Object getPetById(long id);

	Pet createPet(Pet pet);

	Pet updatePet(Pet pet);

	void removePet(long id);

	double calculatePrice(Pet pet);

}
