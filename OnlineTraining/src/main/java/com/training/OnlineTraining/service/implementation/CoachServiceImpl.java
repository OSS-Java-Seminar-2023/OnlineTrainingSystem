package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.CoachRepository;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {
    private final UserService userService;
    private final CoachRepository coachRepository;

    @Override
    public void registerCoach(CoachDto coachDto, UUID userId) {
        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(userId));

        optionalUser.ifPresentOrElse(
                user -> {
                    Coach coach = new Coach();
                    coach.setUser(user);
                    coach.setYearsOfExperience(coachDto.getYearsOfExperience());
                    coach.setEducation(coachDto.getEducation());
                    coach.setMonthlyPrice(coachDto.getMonthlyPrice());

                    coachRepository.save(coach);
                },
                () -> {
                    throw new RuntimeException("User not found");
                }
        );
    }
    @Override
    public boolean isCoach(User user) {return coachRepository.existsByUser(user);
    }
}
