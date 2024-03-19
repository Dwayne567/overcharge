package com.codeWithProjects.ecom.services;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.codeWithProjects.ecom.dto.ChangePasswordDto;
import com.codeWithProjects.ecom.dto.SignupRequest;
import com.codeWithProjects.ecom.dto.admin.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);

    Boolean hasUserWithEmail(String email);
    
    UserDto getUserById(Long userId);

    UserDto updateUser(UserDto userDto) throws IOException;

   ResponseEntity<?> updatePasswordById(ChangePasswordDto changePasswordDto);
}
