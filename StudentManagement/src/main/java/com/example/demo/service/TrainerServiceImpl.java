package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TrainerDto;
import com.example.demo.entity.Batch;
import com.example.demo.entity.Trainer;
import com.example.demo.entity.Users;
import com.example.demo.exception.BatchServiceImplException;
import com.example.demo.exception.EnrollmentServiceImplException;
import com.example.demo.exception.TrainerServiceImplException;
import com.example.demo.interfaces.TrainerService;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	TrainerRepository trainerRepository;
	@Autowired
	UsersRepository usersRepository;

	@Autowired
	BatchRepository batchRepository;

	public void addTrainer(TrainerDto dto) {
		Optional<Users> u = usersRepository.findById(dto.getUserId());
		if (u.isEmpty()) {
			throw new TrainerServiceImplException("User is not found for id ", HttpStatus.NOT_FOUND);
		}
		Users user = u.get();

		Trainer trainer = new Trainer();
		trainer.setName(dto.getName());
		trainer.setExp(dto.getExp());
		trainer.setSubject(dto.getSubject());
		trainer.setUsers(user);
		try {
			trainerRepository.save(trainer);
		} catch (Exception e) {
			throw new TrainerServiceImplException("User is not found for id ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		user.setRole("ROLE_TRAINER");
		usersRepository.save(user);
	}

	@Override
	public Trainer getTrainerById(long tId) {
		Optional<Trainer> t = trainerRepository.findById(tId);
		if (t.isEmpty()) {
			throw new TrainerServiceImplException("Not Found", HttpStatus.NO_CONTENT);
		}
		return t.get();
	}

	@Override
	public List<Trainer> getTrainers() {
		List<Trainer> trainer = trainerRepository.findAll();
		if (trainer.isEmpty()) {
			throw new TrainerServiceImplException("Not Found", HttpStatus.NO_CONTENT);
		}
		return trainer;
	}

	@Override
	public void updateTrainerById(TrainerDto dto, long tId) {
		Optional<Trainer> t = trainerRepository.findById(tId);
		if (t.isEmpty()) {
			throw new TrainerServiceImplException("Trainer is not found ", HttpStatus.NOT_FOUND);
		}
		Trainer trainer = t.get();
		trainer.setName(dto.getName());
		trainer.setExp(dto.getExp());
		trainer.setSubject(dto.getSubject());
		try {
			trainerRepository.save(trainer);
		} catch (Exception e) {
			throw new TrainerServiceImplException("user not update", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteTrainerById(long tId) {
		Optional<Trainer> t = trainerRepository.findById(tId);
		if (t.isEmpty()) {
			throw new TrainerServiceImplException("Not Found", HttpStatus.NO_CONTENT);
		}
		try {
			trainerRepository.deleteById(tId);
		} catch (Exception e) {
			throw new TrainerServiceImplException("Unable to delete", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@Override
	public void assignTrainer(int batchId, long trainerId) {
		// TODO Auto-generated method stub
		Optional<Batch> b = batchRepository.findById(batchId);
		if (b.isEmpty()) {
			throw new EnrollmentServiceImplException("Batch Not Found", HttpStatus.NOT_FOUND);
		}

		Batch batch = b.get();

		Optional<Trainer> t = trainerRepository.findById(trainerId);
		if (t.isEmpty()) {
			throw new EnrollmentServiceImplException("Trainer Not Found", HttpStatus.NOT_FOUND);
		}

		Trainer trainer = t.get();
		if (trainer.getBatches().contains(batch)) 
			throw new EnrollmentServiceImplException("Trainer already assigned",HttpStatus.CONFLICT); 
		trainer.getBatches().add(batch);
		trainerRepository.save(trainer);
	}

}
