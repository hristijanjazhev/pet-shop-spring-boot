package com.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.service.IPurchasePetService;

@RestController
@RequestMapping("/")
public class PurchasePetController {
	@Autowired
	private IPurchasePetService purchasePetService;

	@PostMapping("buyPet/{petId}/{userId}")
	public ResponseEntity<Object> buyPet(@PathVariable Long petId, @PathVariable Long userId) {
		return ResponseEntity.ok().body(this.purchasePetService.purchasePet(petId, userId));
	}
}