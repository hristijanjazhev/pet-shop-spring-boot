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

import com.petshop.model.Pet;
import com.petshop.service.IPetService;

@RestController
@RequestMapping("/all")
public class PetShopController {

	@Autowired
	private final IPetService petService;

	public PetShopController(IPetService petService) {
		this.petService = petService;
	}

	@GetMapping("/pets")
	public ResponseEntity<List<Pet>> getPets() {
		return ResponseEntity.ok().body(this.petService.getAllPets());
	}

	@GetMapping("/pet/{id}")
	public ResponseEntity<Object> getPetsById(@PathVariable long id) {
		return ResponseEntity.ok().body(this.petService.getPetById(id));
	}

	@PutMapping("/pet/{id}")
	public ResponseEntity<Pet> updatePets(@PathVariable long id, @RequestBody Pet pet) {
		pet.setId(id);
		return ResponseEntity.ok().body(this.petService.updatePet(pet));
	}

	@PostMapping("/pet")
	public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
		return ResponseEntity.ok().body(this.petService.createPet(pet));
	}

	@DeleteMapping("/pet/{id}")
	public HttpStatus removePet(@PathVariable long id) {
		this.petService.removePet(id);
		return HttpStatus.OK;
	}
}
