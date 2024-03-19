package com.codeWithProjects.ecom.services.admin;

import com.codeWithProjects.ecom.dto.admin.UserDto;
import com.codeWithProjects.ecom.entity.Deck;
import com.codeWithProjects.ecom.entity.User;
import com.codeWithProjects.ecom.repository.DeckRepository;
import com.codeWithProjects.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
 
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(User::getUserDto).collect(Collectors.toList());
    }
    
    public List<UserDto> searchUserByName(String name){
        List<User> users = userRepository.findAllByNameContaining(name);
        return users.stream().map(User::getUserDto).collect(Collectors.toList());
    }
    
	public boolean deleteUser(Long id){
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			userRepository.deleteById(id);
			return true;
		}
		return false;
	}
    
}
