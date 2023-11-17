package com.training.OnlineTraining.service;

import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void checkRegisterUser() {

        User request = new User();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");
        request.setStreet("street");
        request.setCity("city");
        request.setCountry("country");
        request.setPhoneNumber("123456789");
        request.setGender("Male");
        request.setAge(25);
        request.setPassword("password");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(request);

        // Act
        User registeredUser = userService.registerUser(request);

        // Assert
        assertNotNull(registeredUser);
        assertEquals("John", registeredUser.getFirstName());
        assertEquals("Doe", registeredUser.getLastName());
        assertEquals("john.doe@example.com", registeredUser.getEmail());
        assertEquals("street", registeredUser.getStreet());
        assertEquals("city", registeredUser.getCity());
        assertEquals("country", registeredUser.getCountry());
        assertEquals("123456789", registeredUser.getPhoneNumber());
        assertEquals("Male", registeredUser.getGender());
        assertEquals(25, registeredUser.getAge());
        assertEquals("password", registeredUser.getPassword());

        verify(userRepository, times(1)).findByEmail(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }
}

