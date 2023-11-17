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

    private boolean areInputsInvalid(String firstName, String lastName, String email, String address, String phoneNumber, String gender, Integer age, String password) {
        return firstName == null || lastName == null || email == null || address == null || phoneNumber == null || gender == null || age == null || password == null || age <= 0;
    }

    public User registerUser(User request) {
        if (areInputsInvalid(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getAddress(), request.getPhoneNumber(), request.getGender(),
                request.getAge(), request.getPassword())) {
            return null;
        } else if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Duplicate email");
            return null;
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());

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

