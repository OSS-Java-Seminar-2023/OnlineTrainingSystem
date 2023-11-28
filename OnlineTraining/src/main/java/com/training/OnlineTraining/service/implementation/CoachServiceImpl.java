package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.repository.CoachRepository;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


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

    @Override
    public List<CoachDto> getAllCoaches() {
        return coachRepository.findAll().stream()
                .map(coach -> {
                    CoachDto coachDto = new CoachDto();
                    coachDto.setYearsOfExperience(coach.getYearsOfExperience());
                    coachDto.setEducation(coach.getEducation());
                    coachDto.setMonthlyPrice(coach.getMonthlyPrice());

                    User user = coach.getUser();
                    coachDto.setUserFirstName(user.getFirstName());
                    coachDto.setUserLastName(user.getLastName());
                    coachDto.setUserCity(user.getCity());
                    coachDto.setUserCountry(user.getCountry());
                    coachDto.setUserGender(user.getGender());
                    coachDto.setUserAge(user.getAge());
                    return coachDto;
                })
                .collect(Collectors.toList());
    }
}
