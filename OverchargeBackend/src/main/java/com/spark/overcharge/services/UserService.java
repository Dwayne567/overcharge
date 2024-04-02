package com.spark.overcharge.services;

import com.spark.overcharge.dto.UserDto;
import com.spark.overcharge.security.SignupRequest;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UserService {
	UserDto createUser(SignupRequest signupRequest);

	List<UserDto> getAllUsers();

	UserDto getUserById(Long userId);

	List<UserDto> searchUserByName(String name);

	public UserDto findFirstByEmail(String email);

	UserDto updateUser(UserDto userDto) throws IOException;

	ResponseEntity<?> updatePasswordById(UserDto userDto);

	public boolean deleteUser(Long id);

	Boolean hasUserWithEmail(String email);
}
