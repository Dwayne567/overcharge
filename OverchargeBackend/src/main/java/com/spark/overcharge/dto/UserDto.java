package com.spark.overcharge.dto;

import org.springframework.web.multipart.MultipartFile;

import com.spark.overcharge.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    
    private String name;

    private String email;

    private UserRole role;

    private MultipartFile img;

    private byte[] returnedImg;
    
    private String oldPassword;
    
    private String newPassword;

}