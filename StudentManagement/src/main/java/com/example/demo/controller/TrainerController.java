package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TrainerDto;
import com.example.demo.entity.Trainer;
import com.example.demo.interfaces.TrainerService;

@RestController
@RequestMapping("admin")
public class TrainerController {
	@Autowired
	TrainerService service;

	@PostMapping("/trainer")
	public ResponseEntity<String> addTrainer(@RequestBody TrainerDto t) {
		service.addTrainer(t);
		return new ResponseEntity("Trainer is Added", HttpStatus.CREATED);
	}

	@GetMapping("trainer/{tId}")
	public ResponseEntity<Trainer> getTrainerById(@PathVariable long tId) {
		return new ResponseEntity(service.getTrainerById(tId), HttpStatus.FOUND);
	}

	@GetMapping("trainers")
	public ResponseEntity<List<Trainer>> getAllTrainer() {
		return new ResponseEntity(service.getTrainers(), HttpStatus.FOUND);
	}

	@PutMapping("trainer/{tId}")
	public ResponseEntity updateBatch(@RequestBody TrainerDto t, @PathVariable long tId) {
		service.updateTrainerById(t, tId);
		return new ResponseEntity("Trainer is updated", HttpStatus.OK);
	}

	@DeleteMapping("trainer/{tId}")
	public ResponseEntity<Trainer> deleteTrainer(@PathVariable long tId) {
		service.deleteTrainerById(tId);
		return new ResponseEntity("Trainer is Deleted", HttpStatus.OK);
	}

	@PostMapping("assigntrainer/{batchid}/{trainerid}")
	public ResponseEntity<String> assignBatch(@PathVariable int batchid, @PathVariable Long trainerid) {
		service.assignTrainer(batchid, trainerid);
		return new ResponseEntity<String>("Trainer has successfully assign", HttpStatus.OK);
	}

}
