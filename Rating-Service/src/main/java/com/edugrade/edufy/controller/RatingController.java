package com.edugrade.edufy.controller;

import java.util.List;

import javax.validation.Valid;

import com.edugrade.edufy.models.Media3;
import com.edugrade.edufy.services.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugrade.edufy.models.Rating;
import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.dto.RatingDTO;
import com.edugrade.edufy.models.dto.UserDTO;
import com.edugrade.edufy.services.impl.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class RatingController {

	@Autowired
	private UserService userService;

	@Autowired
	private RatingService ratingService;

	public RatingController() {
	}

	@PostMapping("/{userId}/ratings/")
	public ResponseEntity<RatingDTO> addRating(@PathVariable Long userId, @Valid @RequestBody Rating rating) {
		return ResponseEntity.ok(userService.addRating(userId, rating));
	}
	
	@PutMapping("/{userId}/ratings/")
	public ResponseEntity<RatingDTO> updateRating(@PathVariable Long userId, @Valid @RequestBody Rating rating) {
		return ResponseEntity.ok(userService.updateRating(userId, rating));
	}
	
	@GetMapping("/{userId}/ratings/")
	public ResponseEntity<List<RatingDTO>> getAllRatedMediaForUser(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getRatedMedia(userId));
	}
	
	@DeleteMapping("/{userId}/ratings/{mediaId}")
	public ResponseEntity<String> deleteRating(@PathVariable Long userId, @PathVariable String mediaId) {
		return ResponseEntity.ok(userService.deleteRating(userId, mediaId));
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.addUser(user));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUser(userId));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.deleteUser(userId));
	}
	@GetMapping("/recomendation/{userId}")
	public ResponseEntity<List<Media3>> getRecomendations(@PathVariable Long userId){
		return ResponseEntity.ok((ratingService.getRecommendationsForUser(userId)));
	}



	

}
