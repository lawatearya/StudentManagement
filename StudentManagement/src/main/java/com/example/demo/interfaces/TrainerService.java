package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.dto.TrainerDto;
import com.example.demo.entity.Trainer;

public interface TrainerService {
	void addTrainer(TrainerDto dto);

	Trainer getTrainerById(long tId);

	List<Trainer> getTrainers();

	void updateTrainerById(TrainerDto t, long tId);

	void deleteTrainerById(long tId);

	public void assignTrainer(int batchId, long trainerId);
}
