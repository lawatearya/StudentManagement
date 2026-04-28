package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.exception.UsersServiceException;
import com.example.demo.repository.UsersRepository;

@Service
public class userDetailServiceImpl implements UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Users> o = usersRepository.findByUsername(username);
		if (o.isEmpty()) {
			throw new UsersServiceException("Not Found", HttpStatus.NOT_FOUND);
		}
		Users user = o.get();

		List<GrantedAuthority> lga = new ArrayList<GrantedAuthority>();

		for (String roles : Arrays.asList(user.getRole().split(","))) {
			GrantedAuthority ga = new SimpleGrantedAuthority(roles.trim());
			lga.add(ga);
		}

		User us = new User(user.getUsername(), user.getPassword(), lga);
		return us;
	}

}
