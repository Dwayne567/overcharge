package com.codeWithProjects.ecom.dto.admin;

import com.codeWithProjects.ecom.enums.UserRole;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    
    private String name;

    private String email;

    private UserRole role;

    private MultipartFile img;

    private byte[] returnedImg;

}
