package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsersRegistrationDto;
import com.example.demo.entity.Users;
import com.example.demo.exception.UsersServiceException;
import com.example.demo.interfaces.UsersService;
import com.example.demo.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public void userRegisteration(UsersRegistrationDto usersRegistrationDto) {

		checksEmptyFields(usersRegistrationDto);

		Users users = new Users();
		users.setUsername(usersRegistrationDto.getUsername());
		users.setEmail(usersRegistrationDto.getEmail());
		users.setMobileNo(usersRegistrationDto.getMobileNo());
		users.setPassword(passwordEncoder.encode(usersRegistrationDto.getPassword()));
		users.setDate(LocalDate.now());
		users.setRole("ROLE_USER");
		usersRepository.save(users);
	}

	private void checksEmptyFields(UsersRegistrationDto usersRegistrationDto) {
		if (usersRegistrationDto.getUsername() == null || usersRegistrationDto.getEmail() == null
				|| usersRegistrationDto.getMobileNo() <= 10 || usersRegistrationDto.getPassword() == null) {
			throw new UsersServiceException("Empty Fields", HttpStatus.BAD_GATEWAY);
		}
	}

	@Override
	public Users getUserById(long uid) {
		Optional<Users> u = usersRepository.findById(uid);
		if (u.isEmpty()) {
			throw new UsersServiceException("User is not found of id " + uid, HttpStatus.NO_CONTENT);
		}
		return u.get();
	}

	@Override
	public List<Users> getUsers() {
		List<Users> u = usersRepository.findAll();
		if (u.isEmpty()) {
			throw new UsersServiceException("No Users available", HttpStatus.NO_CONTENT);
		}
		return u;
	}

	@Override
	public Users updateUserById(Users u, long uid) {
		Users exist = getUserById(uid);
		exist.setUsername(u.getUsername());
		exist.setEmail(u.getEmail());
		exist.setMobileNo(u.getMobileNo());
		exist.setPassword(u.getPassword());
		exist.setAddress(u.getAddress());
		exist.setAge(u.getAge());
		exist.setDateOfBirth(u.getDateOfBirth());
		exist.setGender(u.getGender());
		exist.setAdharNo(u.getAdharNo());
		exist.setPassoutYear(u.getPassoutYear());
		exist.setProfession(u.getProfession());
		exist.setExpertise(u.getExpertise());
		exist.setParentName(u.getParentName());
		exist.setParentMobileNo(u.getParentMobileNo());
		exist.setDate(u.getDate());
		exist.setRole(u.getRole());
		return usersRepository.save(exist);
	}

	@Override
	public void deleteUserById(long uid) {
		Users exist = getUserById(uid);
		usersRepository.delete(exist);
	}

}
