package com.edugrade.edufy.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edugrade.edufy.exceptions.ResourceNotFoundException;
import com.edugrade.edufy.exceptions.UserAlreadyExistException;
import com.edugrade.edufy.models.Rating;
import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.dto.RatingDTO;
import com.edugrade.edufy.models.dto.UserDTO;
import com.edugrade.edufy.repositories.UserRepository;
import com.edugrade.edufy.services.UserServiceInterface;

@Service
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RatingService ratingService;

	public UserService() {
	}

	@Override
	public UserDTO addUser(User user) {
		checkIfUserExists(user.getUserId());
		return new UserDTO(userRepository.save(user).getUserId(), new ArrayList<>());
	}

	@Override
	public UserDTO getUser(Long userId) {
		return new UserDTO(getUserById(userId).getUserId(), getRatedMedia(userId));
	}

	@Override
	public List<UserDTO> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> new UserDTO(user.getUserId(), getRatedMedia(user.getUserId()))).toList();
	}

	@Override
	public String deleteUser(Long userId) {
		userRepository.delete(getUserById(userId));
		return String.format("Deleted user with ID: '%s'", userId);
	}

	public RatingDTO addRating(Long userId, Rating rating) {
		User user = getUserById(userId);
		return getRatingFromUser(userId, rating.getMediaId()).map(ratingService::getMediaDetails).orElseGet(() -> {
			ratingService.addRating(rating);
			user.addRating(rating);
			userRepository.save(user);
			return ratingService.getMediaDetails(rating);
		});
	}

	public RatingDTO updateRating(Long userId, Rating rating) {
		return getRatingFromUserOrElseThrow(userId, rating.getMediaId())
				.map(ratingToUpdate -> ratingService.updateRatingForMedia(ratingToUpdate.getRatingId(), rating)).get();
	}

	public String deleteRating(Long userId, String mediaId) {
		User user = getUserById(userId);
		return getRatingFromUserOrElseThrow(userId, mediaId).map(ratingToRemove -> {
			user.removeRating(ratingToRemove);
			userRepository.save(user);
			return ratingToRemove;
		}).map(ratingToRemove -> ratingService.deleteRating(ratingToRemove.getRatingId())).get();
	}

	public List<RatingDTO> getRatedMedia(Long userId) {
		return getUserById(userId).getRatings().stream().map(ratingService::getMediaDetails).toList();
	}

	private Optional<Rating> getRatingFromUser(Long userId, String mediaId) {
		return getUserById(userId).getRatings().stream()
				.filter(ratedMedia -> ratedMedia.getMediaId().equals(mediaId)).findFirst();
	}
	
	private Optional<Rating> getRatingFromUserOrElseThrow(Long userId, String mediaId) throws ResourceNotFoundException{
		return Optional.of(getRatingFromUser(userId, mediaId).orElseThrow(() -> new ResourceNotFoundException("Rating for media", "ID", mediaId)));
	}

	private User getUserById(Long userId) throws ResourceNotFoundException {
		return userRepository.findByUserId(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
	}

	private void checkIfUserExists(Long userId) throws UserAlreadyExistException {
		if (userRepository.existsById(userId))
			throw new UserAlreadyExistException(userId);
	}

}
