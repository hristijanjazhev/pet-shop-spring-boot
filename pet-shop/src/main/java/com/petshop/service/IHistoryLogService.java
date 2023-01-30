package com.petshop.service;

import java.util.List;

import com.petshop.model.HistoryLog;

public interface IHistoryLogService {
	List<HistoryLog> getHistoryLog();
	// HistoryLog logBuyAttempt(List<User> users, boolean success);

}
