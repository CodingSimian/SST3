package com.edugrade.edufy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edugrade.edufy.models.PlayedMedia;
import com.edugrade.edufy.models.User;
import com.edugrade.edufy.models.dto.PlayedMediaDTO;
import com.edugrade.edufy.models.dto.UserDTO;
import com.edugrade.edufy.services.impl.UserService;

@RestController
@RequestMapping("api/v1/users")
public class MediaPlayedController {

	@Autowired
	private UserService userService;

	public MediaPlayedController() {
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserDetails(userId));
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PostMapping("/")
	public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.addUser(user));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserDetails(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.deleteUserDetails(userId));
	}

	@PostMapping("/{userId}/playedmedia")
	public ResponseEntity<PlayedMediaDTO> addPlayedMedia(@PathVariable Long userId,
			@Valid @RequestBody PlayedMedia playedMedia) {
		return ResponseEntity.ok(userService.addPlayedMedia(userId, playedMedia));
	}
	
	@DeleteMapping("/playedmedia/{mediaId}")
	public ResponseEntity<String> deleteMedia(@PathVariable String mediaId) {
		return ResponseEntity.ok(userService.deleteMediaById(mediaId));
	}
	
	@GetMapping("/{userId}/playedmedia")
	public ResponseEntity<List<PlayedMediaDTO>> getMostPlayedMedia(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getPlayedMedia(userId));
	}

}
