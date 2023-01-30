package com.petshop.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.model.HistoryLog;
import com.petshop.model.Pet;
import com.petshop.model.User;
import com.petshop.service.implementations.HistoryLogServiceImpl;

@RestController
@RequestMapping("/history-log")
public class HistoryLogController {
	@Autowired
	private final HistoryLogServiceImpl historyLogService;

	public HistoryLogController(HistoryLogServiceImpl historyLogService) {
		this.historyLogService = historyLogService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<HistoryLog>> listHistoryLog() {
		List<HistoryLog> logs = this.historyLogService.getHistoryLog();
		return new ResponseEntity<>(logs, HttpStatus.OK);
	}

	@GetMapping("/pets")
	public ResponseEntity<List<HistoryLog>> getPetPurchaseHistory(Pet pet) {
		List<HistoryLog> petLogs = this.historyLogService.getPetPurchaseHistory(pet);
		return new ResponseEntity<>(petLogs, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<HistoryLog>> getUserPurchaseHistory(User user) {
		List<HistoryLog> petLogs = this.historyLogService.getUserPurchaseHistory(user);
		return new ResponseEntity<>(petLogs, HttpStatus.OK);
	}

	@PostMapping("/set/history-log")
	public Long countHistoryLogs(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		return this.historyLogService.countByEventAndDate("Buy", date);
	}
}
