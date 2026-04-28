package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

}
