package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private boolean areInputsValid(String firstName, String lastName, String email, String address, String phoneNumber, String gender, Integer age, String password) {
        return firstName == null || lastName == null || email == null || address == null || phoneNumber == null || gender == null || age == null || password == null || age <= 0;
    }

    public User registerUser(String firstName, String lastName, String email, String address, String phoneNumber, String gender, Integer age, String password) {

        if (areInputsValid(firstName, lastName, email, address, phoneNumber, gender, age, password)) {
            return null;
        } else if (userRepository.findByEmail(email).isPresent()) {
            System.out.println("Duplicate email");
            return null;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setGender(gender);
        user.setAge(age);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User authenticate(String email, String password){
        return userRepository.findByEmailAndPassword(email, password)
                .map(user -> {
                    user.setId(UUID.fromString(user.getId().toString()));
                    return user;
                })
                .orElse(null);
    }

}

