
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BatchDto;
import com.example.demo.entity.Batch;
import com.example.demo.entity.Course;
import com.example.demo.exception.BatchServiceImplException;
import com.example.demo.interfaces.BatchService;
import com.example.demo.repository.BatchRepository;
import com.example.demo.repository.CourseRepository;

@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private BatchRepository batchRepository;

	@Override
	public void addBatch(BatchDto batchDto) {

		checksEmptyFields(batchDto);

		Optional<Course> optional = courseRepository.findById(batchDto.getCourseId());

		if (optional.isEmpty()) {
			throw new BatchServiceImplException("Course Id Not Found", HttpStatus.NOT_FOUND);
		}

		Course course = optional.get();

		Batch batch = new Batch();
		batch.setName(batchDto.getName());
		batch.setTime(batchDto.getTime());
		batch.setCapacity(batchDto.getCapacity());
		batch.setFees(batchDto.getFees());
		batch.setStartDate(batchDto.getStartDate());
		batch.setMode(batchDto.getMode());
		batch.setCourses(course);

		try {
			batchRepository.save(batch);
		} catch (Exception e) {
			throw new BatchServiceImplException("Error while saving batch", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void checksEmptyFields(BatchDto batchDto) {

		if (batchDto.getName() == null || batchDto.getName().isEmpty() || batchDto.getTime() == null
				|| batchDto.getStartDate() == null || batchDto.getMode() == null || batchDto.getMode().isEmpty()
				|| batchDto.getCapacity() <= 0 || batchDto.getFees() <= 0) {

			throw new BatchServiceImplException("Fields are Empty or Invalid", HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public void deleteBatch(int id) {

		if (!batchRepository.existsById(id)) {
			throw new BatchServiceImplException("Batch not found", HttpStatus.NOT_FOUND);
		}

		try {
			batchRepository.deleteById(id);
		} catch (Exception e) {
			throw new BatchServiceImplException("Error while deleting batch", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteAllBatch() {

		try {
			batchRepository.deleteAll();
		} catch (Exception e) {
			throw new BatchServiceImplException("Error while deleting batches", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Batch getBatch(int id) {

		Optional<Batch> optional = batchRepository.findById(id);

		if (optional.isEmpty()) {
			throw new BatchServiceImplException("Batch not found", HttpStatus.NOT_FOUND);
		}

		return optional.get();
	}

	@Override
	public List<Batch> getAllBatch() {

		try {
			return batchRepository.findAll();
		} catch (Exception e) {
			throw new BatchServiceImplException("Error while fetching batches", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
}
