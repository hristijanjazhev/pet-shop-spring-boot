package com.petshop.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "history-log")
public class HistoryLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "successfulPurchases")
	private boolean successfulPurchases;

	@Column(name = "failedPurchases")
	private boolean failedPurchases;

	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private boolean success;

	private String event;

	public HistoryLog() {
		super();
	}

	public HistoryLog(LocalDateTime now, int size, int i, int j) {
	}

	public HistoryLog(Long id, LocalDate date, boolean successfulPurchases, boolean failedPurchases, boolean success) {
		super();
		this.id = id;
		this.date = date;
		this.successfulPurchases = successfulPurchases;
		this.failedPurchases = failedPurchases;
		this.success = success;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean getSuccessfulPurchases() {
		return successfulPurchases;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccessfulPurchases(boolean success) {
		this.success = success;
	}

	public boolean getFailedPurchases() {
		return failedPurchases;
	}

	public void setFailedPurchases(boolean failedPurchase) {
		this.failedPurchases = failedPurchase;
	}

	public void setEvent(String string) {
		this.event = string;
	}

	public String getEvent() {
		return event;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
