package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private boolean areInputsInvalid(User request) {
        return request.getFirstName() == null ||
                request.getLastName() == null ||
                request.getEmail() == null ||
                request.getStreet() == null ||
                request.getCity() == null ||
                request.getCountry() == null ||
                request.getPhoneNumber() == null ||
                request.getGender() == null ||
                request.getAge() == null ||
                request.getPassword() == null
                || request.getAge() <= 0;
    }

    public User registerUser(User request) {
        if (areInputsInvalid(request)) {
            return null;
        } else if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Duplicate email");
            return null;
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setStreet(request.getStreet());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setAge(request.getAge());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(user -> {
                    user.setId(UUID.fromString(user.getId().toString()));
                    return user;
                })
                .orElse(null);
    }
}

