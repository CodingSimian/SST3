package com.edugrade.edufy.controller;

import java.util.List;

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
import com.edugrade.edufy.models.vo.Media;
import com.edugrade.edufy.services.impl.UserService;

@RestController
@RequestMapping("api/v1/users")
public class MediaPlayedController {

	@Autowired
	private UserService userService;

	public MediaPlayedController() {
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserDetails(userId));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUserDetails(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.deleteUserDetails(userId));
	}

	@PostMapping("/")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return ResponseEntity.ok(userService.addUser(user)); 
	}

	@PostMapping("/{userId}/playedmedia")
	public ResponseEntity<PlayedMedia> addPlayedMedia(@PathVariable Long userId, @RequestBody PlayedMedia playedMedia) {
		return ResponseEntity.ok(userService.addPlayedMedia(userId, playedMedia));
	}

	@GetMapping("/{userId}/playedmedia/mostplayed")
	public ResponseEntity<List<PlayedMedia>> getMostPlayedMedia(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getMostPlayedMedia(userId));
	}
	
	@GetMapping("/{userId}/test")
	public ResponseEntity<List<Media>> getMedia(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getPlayedMedia(userId));
	}
	
	@GetMapping("/{userId}/playedmedia")
	public ResponseEntity<List<PlayedMedia>> getAllPlayedMedia(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getAllPlayedMedia(userId));
	}
}
