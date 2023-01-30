package com.petshop.service.implementations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.model.HistoryLog;
import com.petshop.model.Pet;
import com.petshop.model.User;
import com.petshop.repository.IHistoryLogRepository;
import com.petshop.service.IHistoryLogService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HistoryLogServiceImpl implements IHistoryLogService {
	@Autowired
	private IHistoryLogRepository historyLogRepository;

	public List<HistoryLog> getPetPurchaseHistory(Pet pet) {
		return this.historyLogRepository.findByPet(pet);
	}

	public List<HistoryLog> getUserPurchaseHistory(User user) {
		return this.historyLogRepository.findByUser(user);
	}

	public List<HistoryLog> getAllPurchaseHistory() {
		return this.historyLogRepository.findAll();
	}

	public Long countByEventAndDate(String event, LocalDate date) {
		return this.historyLogRepository.countByEventAndDate(event, date);
	}

	public HistoryLog logBuyAttempt(List<User> users, boolean success) {
		HistoryLog logEntry = new HistoryLog();
		logEntry.setDate(LocalDate.now());
		logEntry.setSuccessfulPurchases(success);
		if (!success) {
			logEntry.setFailedPurchases(success);
		}
		return this.historyLogRepository.save(logEntry);
	}

	@Override
	public List<HistoryLog> getHistoryLog() {
		List<HistoryLog> buyHistoryLogs = this.historyLogRepository.findByEvent("Buy");
		List<HistoryLog> result = new ArrayList<>();
		for (HistoryLog log : buyHistoryLogs) {
			LocalDate date = log.getDate();
			long successfulBuys = this.historyLogRepository.countByEventAndDate("Buy Success", date);
			long failedBuys = this.historyLogRepository.countByEventAndDate("Buy Failed", date);
			log.setEvent(date + "," + successfulBuys + "," + failedBuys);
			result.add(log);
		}
		return result;
	}

}
