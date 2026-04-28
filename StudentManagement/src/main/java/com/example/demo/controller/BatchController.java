package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BatchDto;
import com.example.demo.entity.Batch;
import com.example.demo.interfaces.BatchService;

@RestController
@RequestMapping("/admin")
public class BatchController {

	@Autowired
	BatchService batchServiceInt;

	@PostMapping("/addBatch")
	public ResponseEntity<String> addBatch(@RequestBody BatchDto batchDto) {
		batchServiceInt.addBatch(batchDto);
		return new ResponseEntity<>("Batch added successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteBatche/{id}")
	public ResponseEntity<String> deleteBatch(@PathVariable int id) {
		batchServiceInt.deleteBatch(id);
		return new ResponseEntity<>("Batch deleted", HttpStatus.OK);
	}

	@DeleteMapping("/deleteAllBatches")
	public ResponseEntity<String> deleteAllBatch() {
		batchServiceInt.deleteAllBatch();
		return new ResponseEntity<>("All Batches deleted", HttpStatus.OK);
	}

	@GetMapping("/getBatch/{id}")
	public ResponseEntity<Batch> getBatch(@PathVariable int id) {
		return new ResponseEntity<>(batchServiceInt.getBatch(id), HttpStatus.OK);
	}

	@GetMapping("/getAllBatches")
	public ResponseEntity<List<Batch>> getAllBatch() {
		return new ResponseEntity<>(batchServiceInt.getAllBatch(), HttpStatus.OK);
	}
}
