package com.edugrade.edufy.services;

import java.util.List;

import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.dto.UserDTO;

public interface UserServiceInterface {

	public UserDTO getUserDetails(Long userId);

	public String deleteUserDetails(Long userId);

	public UserDTO addUser(User user);
	
	public List<UserDTO> getAllUsers();

}
