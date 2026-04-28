package com.example.demo.config;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;  

@Component
public class JwtUtil {
	
	private static final String SECRET = "My-Secrete-Key-Student-Management-9834";
	byte[] getBytes = SECRET.getBytes(StandardCharsets.UTF_8);
	private final Key key = Keys.hmacShaKeyFor(getBytes);

	public String generateToken(UserDetails userDetails) {

		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 3600000))
				.signWith(key)
				.compact();
	}
	
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
}
