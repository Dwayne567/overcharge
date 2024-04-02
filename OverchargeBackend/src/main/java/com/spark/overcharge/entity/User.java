package com.spark.overcharge.entity;

import java.util.List;

import com.spark.overcharge.dto.UserDto;
import com.spark.overcharge.enums.UserRole;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    private String email;

    private String password;

    private UserRole role;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Deck> decks;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Card> cards;
    
    public UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setRole(role);
        userDto.setReturnedImg(img);
        return userDto;
    }
}
