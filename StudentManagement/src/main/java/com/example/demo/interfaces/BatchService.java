package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.dto.BatchDto;
import com.example.demo.entity.Batch;

public interface BatchService{
	void addBatch( BatchDto batchDto);

	void deleteBatch(int id);

	void deleteAllBatch();

	Batch getBatch(int id);

	List<Batch> getAllBatch();
}
