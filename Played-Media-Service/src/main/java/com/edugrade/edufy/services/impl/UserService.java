package com.edugrade.edufy.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.edugrade.edufy.exceptions.ResourceNotFoundException;
import com.edugrade.edufy.exceptions.UserAlreadyExistException;
import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.vo.Media;
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
	public User getUserDetails(Long userId) {
		return getUserById(userId);
	}

	@Override
	public String deleteUserDetails(@PathVariable Long userId) {
		userRepository.delete(getUserById(userId));
		return String.format("Deleted user with ID: '%s'", userId);
	}

	@Override
	public User addUser(User user) {
		checkIfUserExists(user.getUserId());
		return userRepository.save(user);
	}

	public List<PlayedMedia> getMostPlayedMedia(Long userId) {
		return playedMediaService.getMostPlayedMedia(userId);
	}

	@Override
	public List<PlayedMedia> getAllPlayedMedia(Long userId) {
		return getUserById(userId).getPlayedMedia();
	}

	public PlayedMedia addPlayedMedia(Long userId, PlayedMedia playedMedia) {
		User user = getUserById(userId);
		return user.getPlayedMedia().stream().filter(t -> t.getMediaId().equals(playedMedia.getMediaId())).findFirst()
				.map(playedMediaService::updatePlayedMedia).orElseGet(() -> {
					user.addPlayedMedia(playedMedia);
					return playedMediaService.addPlayedMedia(playedMedia);
				});
	}

	public List<Media> getPlayedMedia(Long userId) {
		return getAllPlayedMedia(userId).stream().map(playedMediaService::getMediaDetails).collect(Collectors.toList());
	}

	private User getUserById(Long userId) throws ResourceNotFoundException {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
	}

	private void checkIfUserExists(Long userId) {
		if (userRepository.existsById(userId))
			throw new UserAlreadyExistException(userId);
	}

}
