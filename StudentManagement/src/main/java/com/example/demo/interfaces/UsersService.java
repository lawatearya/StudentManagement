package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.dto.UsersRegistrationDto;
import com.example.demo.entity.Users;

public interface UsersService {

	void userRegisteration(UsersRegistrationDto usersRegistrationDto);

	Users getUserById(long uid);

	List<Users> getUsers();

	Users updateUserById(Users u, long uid);

	void deleteUserById(long uid);
	
}
