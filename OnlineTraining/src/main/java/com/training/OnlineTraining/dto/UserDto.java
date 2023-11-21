package com.training.OnlineTraining.dto;

import lombok.Data;

@Data
public class UserDto {
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
}
