package com.spark.overcharge.controller;

import com.spark.overcharge.dto.UserDto;
import com.spark.overcharge.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userService;

	@GetMapping("/api/admin/users")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/api/user/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
		UserDto userDto = userService.getUserById(userId);
		if (userDto != null) {
			return ResponseEntity.ok(userDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/api/update")
	public ResponseEntity<UserDto> updateProfile(@ModelAttribute UserDto userDto) throws IOException {
		UserDto updatedUser = userService.updateUser(userDto);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/api/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody UserDto userDto) {
		try {
			return userService.updatePasswordById(userDto);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
	}

	@DeleteMapping("/api/admin/user/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
		boolean deleted = userService.deleteUser(userId);
		if (deleted) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
