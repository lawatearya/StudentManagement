package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SessionQrToken;

public interface SessionQrTokenRepository extends JpaRepository<SessionQrToken, Integer> {
	Optional<SessionQrToken> findByTokenAndActive(String token, boolean active);

	Optional<SessionQrToken> findBySessionIdAndActive(int sessionId, boolean active);
}
