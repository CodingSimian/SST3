package com.edugrade.edufy.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edugrade.edufy.exceptions.ResourceNotFoundException;
import com.edugrade.edufy.exceptions.UserAlreadyExistException;
import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.dto.PlayedMediaDTO;
import com.edugrade.edufy.models.dto.UserDTO;
import com.edugrade.edufy.repositories.UserRepository;
import com.edugrade.edufy.services.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private PlayedMediaService playedMediaService;

	@Autowired
	private UserRepository userRepository;

	public UserService() {
	}

	@Override
	public UserDTO getUserDetails(Long userId) {
		return new UserDTO(getUserById(userId).getUserId(), getPlayedMedia(userId));
	}

	@Override
	public String deleteUserDetails(Long userId) {
		userRepository.delete(getUserById(userId));
		return String.format("Deleted user with ID: '%s'", userId);
	}

	@Override
	public UserDTO addUser(User user) {
		checkIfUserExists(user.getUserId());
		return new UserDTO(userRepository.save(user).getUserId(), new ArrayList<>());
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> new UserDTO(user.getUserId(), getPlayedMedia(user.getUserId()))).toList();
	}

	public PlayedMediaDTO addPlayedMedia(Long userId, PlayedMedia playedMedia) {
		User user = getUserById(userId);
		return user.getPlayedMedia().stream().filter(media -> media.getMediaId().equals(playedMedia.getMediaId()))
				.findFirst().map(playedMediaService::updatePlayedMedia).orElseGet(() -> {
					user.addPlayedMedia(playedMedia);
					return playedMediaService.addPlayedMedia(playedMedia);
				});
	}

	public List<PlayedMediaDTO> getPlayedMedia(Long userId) {
		return getUserById(userId).getPlayedMedia().stream().map(playedMediaService::getMediaDetails)
				.collect(Collectors.toList());
	}

	public String deleteMediaById(String mediaId) {
		removeMediaFromUsers(mediaId);
		return playedMediaService.deletePlayedMedia(mediaId);
	}

	private void removeMediaFromUsers(String mediaId) {
		userRepository.findAll().forEach(user -> {
			user.getPlayedMedia().removeIf(media -> media.getMediaId().equals(mediaId));
		});
	}

	private User getUserById(Long userId) throws ResourceNotFoundException {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
	}

	private void checkIfUserExists(Long userId) {
		if (userRepository.existsById(userId))
			throw new UserAlreadyExistException(userId);
	}
}
