package com.petshop.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "description")
	private String description;

	@Column(name = "birthDate")
	protected LocalDate birthDate;

	@Column(name = "price")
	private double price;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "owned")
	private boolean owned;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id", unique = true, nullable = true)
	protected User owner;
//
//	public void getAge() {
//		ChronoUnit.YEARS.between(birthDate, LocalDate.now());
//	}

	public Pet() {
	}

	public void calculatePrice(Pet pet) {
		Dog dog = new Dog();
		if (this.type.equalsIgnoreCase("cat")) {
			int ageInYears = Period.between(this.birthDate, LocalDate.now()).getYears();
			this.price = ageInYears * 1.0;
		} else if (this.type.equalsIgnoreCase("dog")) {
			int ageInYears = Period.between(this.birthDate, LocalDate.now()).getYears();
			double rating = dog.getRating();
			this.price = (ageInYears * 1.0) + (rating * 1.0);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BigDecimal setPrice(BigDecimal price) {
		return price;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean getOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}
}
