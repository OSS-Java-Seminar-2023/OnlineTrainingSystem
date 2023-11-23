package com.training.OnlineTraining.service;

import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoachService {

    private final UserService userService;
    private final CoachRepository coachRepository;

    public void registerCoach(Coach coach) {
        User user = userService.getUserById(coach.getUser().getId());

        if (user == null) {
            throw new RuntimeException("User not found");
        }
        coach.setUser(user);
        coachRepository.save(coach);
    }
    public boolean isCoach(User user) {
        return coachRepository.existsByUser(user);
    }
}
