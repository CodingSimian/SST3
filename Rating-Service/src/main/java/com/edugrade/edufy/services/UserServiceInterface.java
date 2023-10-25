package com.edugrade.edufy.services;

import java.util.List;

import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.dto.UserDTO;

public interface UserServiceInterface {

	UserDTO addUser(User user);

	String deleteUser(Long userId);

	UserDTO getUser(Long userId);
	
	List<UserDTO> getAllUsers();

}
