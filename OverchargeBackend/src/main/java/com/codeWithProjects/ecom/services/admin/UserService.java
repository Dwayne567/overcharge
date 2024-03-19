package com.codeWithProjects.ecom.services.admin;

import com.codeWithProjects.ecom.dto.admin.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<UserDto> searchUserByName(String name);
    //UserDto getUserById(Long userId);
    public boolean deleteUser(Long id);
}
