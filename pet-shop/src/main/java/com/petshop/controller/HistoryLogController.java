package com.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.model.HistoryLog;
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
}
