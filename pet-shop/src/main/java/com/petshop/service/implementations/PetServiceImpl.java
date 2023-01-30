package com.petshop.service.implementations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.petshop.exception.ResourceNotFoundException;
import com.petshop.model.Dog;
import com.petshop.model.Pet;
import com.petshop.repository.IPetRepository;
import com.petshop.service.IPetService;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Component
public class PetServiceImpl implements IPetService {
	@Autowired
	private IPetRepository petRepository;

	public PetServiceImpl(IPetRepository petRepository) {
		this.petRepository = petRepository;
	}

	public void addPet(Pet pet) {
		pet.setOwned(false);
		this.petRepository.save(pet);
	}

	@Override
	public List<Pet> getAllPets() {
		return this.petRepository.findAll();
	}

	@Override
	public Pet createPet(Pet pet) {
		List<Pet> allPets = this.petRepository.findAll();
		if (allPets.size() >= 10) {
			throw new ResourceNotFoundException("Cannot create more than 10 users");
		}
		pet.setName(pet.getName());
		pet.setType(pet.getType());
		pet.setDescription(pet.getDescription());
		pet.setBirthDate(pet.getBirthDate());
		pet.setPrice(calculatePrice(pet));
		pet.setRating(pet.getRating());
		pet.setOwned(pet.getOwned());
		pet.setOwner(pet.getOwner());
		return this.petRepository.save(pet);
	}

	@Override
	public double calculatePrice(Pet pet) {
		double price = ChronoUnit.YEARS.between(pet.getBirthDate(), LocalDate.now()) * 1;
		if (pet instanceof Dog) {
			price += ((Dog) pet).getRating() * 1;
		}
		return price;
	}

	@Override
	public Object getPetById(long id) {
		Optional<Pet> petByIdDB = this.petRepository.findById(id);
		if (petByIdDB.isPresent()) {
			return petByIdDB.get();
		} else {
			throw new ResourceNotFoundException("Record not found with id: !" + id);
		}
	}

	@Override
	public void removePet(long id) {
		this.petRepository.deleteById(id);
	}

	@Override
	public Pet updatePet(Pet pet) {
		Optional<Pet> petDb = this.petRepository.findById(pet.getId());
		if (petDb.isPresent()) {
			Pet petUpdate = petDb.get();

			petUpdate.setId(pet.getId());
			pet.setOwner(pet.getOwner());
			pet.setName(pet.getName());
			pet.setType(pet.getType());
			pet.setDescription(pet.getDescription());
			pet.setBirthDate(pet.getBirthDate());
			pet.setPrice(pet.getPrice());
			pet.setRating(pet.getRating());
			this.petRepository.save(petUpdate);
			return petUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id: !" + pet.getId());
		}
	}

}
