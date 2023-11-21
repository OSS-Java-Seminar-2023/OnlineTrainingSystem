package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.mapper.UserMapper;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.UserRepository;
import com.training.OnlineTraining.util.PasswordUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private boolean areInputsInvalid(UserDto request) {
        return userMapper.isStringNullOrEmpty(request.getFirstName()) ||
                userMapper.isStringNullOrEmpty(request.getLastName()) ||
                userMapper.isStringNullOrEmpty(request.getEmail()) ||
                userMapper.isStringNullOrEmpty(request.getStreet()) ||
                userMapper.isStringNullOrEmpty(request.getCity()) ||
                userMapper.isStringNullOrEmpty(request.getCountry()) ||
                userMapper.isStringNullOrEmpty(request.getPhoneNumber()) ||
                userMapper.isStringNullOrEmpty(request.getGender()) ||
                userMapper.isAgeInvalid(request.getAge()) ||
                userMapper.isStringNullOrEmpty(request.getPassword());
    }

    public User registerUser(UserDto request) {
        if (areInputsInvalid(request)) {
            throw new RuntimeException("Invalid user input");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Duplicate email");
        }

        User user = new User();
        userMapper.mapFieldsWithoutHashing(request, user);
        user.setPassword(userMapper.hashPassword(request.getPassword()));

        return userRepository.save(user);
    }


    public User authenticate(String email, String enteredPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (PasswordUtils.verifyPassword(enteredPassword, user.getPassword())) {
                user.setId(UUID.fromString(user.getId().toString()));
                return user;
            }
        }
        throw new RuntimeException("Authentication failed");
    }
}
