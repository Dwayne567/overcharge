package com.spark.overcharge.services;

import com.spark.overcharge.dto.UserDto;
import com.spark.overcharge.entity.User;
import com.spark.overcharge.enums.UserRole;
import com.spark.overcharge.repository.UserRepository;
import com.spark.overcharge.security.SignupRequest;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void createAdminAccount() {
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		if (null == adminAccount) {
			User user = new User();
			user.setEmail("admin@test.com");
			user.setName("admin");
			user.setRole(UserRole.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
	@Transactional
	public UserDto createUser(SignupRequest signupRequest) {
		User user = new User();

		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setRole(UserRole.USER);

		User createdUser = userRepository.save(user);
		UserDto createdUserDto = new UserDto();

		createdUserDto.setId(createdUser.getId());
		return createdUserDto;
	}

	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(User::getUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		UserDto userDto = new UserDto();
		if (optionalUser.isPresent()) {
			userDto = optionalUser.get().getUserDto();
		}
		return userDto;
	}

	public List<UserDto> searchUserByName(String name) {
		List<User> users = userRepository.findAllByNameContaining(name);
		return users.stream().map(User::getUserDto).collect(Collectors.toList());
	}

	public UserDto findFirstByEmail(String email) {
	    Optional<User> optionalUser = userRepository.findFirstByEmail(email);
		UserDto userDto = new UserDto();
		if (optionalUser.isPresent()) {
			userDto = optionalUser.get().getUserDto();
		}
		return userDto;
	}
	
	@Transactional
	@Override
	public UserDto updateUser(UserDto userDto) throws IOException {
		Optional<User> userOptional = userRepository.findById(userDto.getId());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setEmail(userDto.getEmail());
			user.setName(userDto.getName());
			if (userDto.getImg() != null) {
				user.setImg(userDto.getImg().getBytes());
			}

			User updatedUser = userRepository.save(user);
			UserDto updatedUserDto = new UserDto();
			updatedUserDto.setId(updatedUser.getId());
			return updatedUserDto;
		} else {
			return null;
		}
	}
	
	@Transactional
	@Override
	public ResponseEntity<?> updatePasswordById(UserDto userDto) {
		User user = null;
		try {
			Optional<User> userOptional = userRepository.findById(userDto.getId());
			if (userOptional.isPresent()) {
				user = userOptional.get();
				if (this.bCryptPasswordEncoder.matches(userDto.getOldPassword(), user.getPassword())) {
					user.setPassword(bCryptPasswordEncoder.encode(userDto.getNewPassword()));
					user = userRepository.save(user);
					return ResponseEntity.status(HttpStatus.OK).body(userDto);
				} else {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Old password is incorrect");
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}

	public boolean deleteUser(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Boolean hasUserWithEmail(String email) {
		return userRepository.findFirstByEmail(email).isPresent();
	}
}
