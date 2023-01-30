package com.petshop.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petshop.model.HistoryLog;
import com.petshop.model.Pet;
import com.petshop.model.User;
import com.petshop.service.implementations.HistoryLogServiceImpl;

public interface IHistoryLogRepository extends JpaRepository<HistoryLog, Long> {

	void save(HistoryLogServiceImpl entry);

	List<HistoryLog> findByEvent(String event);

	List<HistoryLog> findByPet(Pet Pet);

	List<HistoryLog> findByUser(User user);

	Long countByEventAndDate(String event, LocalDate date);

}
