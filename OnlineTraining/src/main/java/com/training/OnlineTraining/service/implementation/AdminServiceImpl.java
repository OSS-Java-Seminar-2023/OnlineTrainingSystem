package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.mapper.UserAdminMapper;
import com.training.OnlineTraining.mapper.UserMapper;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.UserRepository;
import com.training.OnlineTraining.service.AdminService;
import com.training.OnlineTraining.specification.UserSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserAdminMapper userAdminMapper;
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByRole(String role) {
        Specification<User> spec = UserSpecifications.filterByRole(role);

        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        List<User> filteredUsers = userRepository.findAll(spec, sort);

        return filteredUsers.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public void updateUser(UUID userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        userAdminMapper.updateEntityFromDto(userDto, user);
        userRepository.save(user);
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        return userMapper.convertToDto(user);
    }

}
