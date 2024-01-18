package com.training.OnlineTraining.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String country;
    private String phoneNumber;
    private String gender;
    private Integer age;
    private String password;

    public UserDto() {

    }

    public UserDto(String email, String password) {

        this.email = email;
        this.password = password;
    }

}
