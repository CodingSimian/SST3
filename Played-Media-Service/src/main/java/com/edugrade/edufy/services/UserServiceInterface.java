package com.edugrade.edufy.services;

import java.util.List;

import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.User;

public interface UserServiceInterface {

	public User getUserDetails(Long userId);

	public String deleteUserDetails(Long userId);

	public User addUser(User user);

	public List<PlayedMedia> getAllPlayedMedia(Long userId);

}
