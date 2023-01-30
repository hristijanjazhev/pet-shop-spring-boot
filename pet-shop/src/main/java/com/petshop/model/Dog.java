package com.petshop.model;

import java.time.LocalDate;

public class Dog extends Pet {
	private int rating;

	public Dog() {
		super();
	}

	public Dog(Long id, User owner, String name, String type, String description, LocalDate birthDate, double price,
			Integer rating) {
	}

	public Integer getRating() {
		return rating;
	}

//	public void calculatePrice() {
//		LocalDate today = LocalDate.now();
//		int age = today.getYear() - birthDate.getYear();
//		int a = age * 1;
//	}
}
