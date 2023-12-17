package com.training.OnlineTraining.service.implementation;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.dto.CoachFilterParams;
import com.training.OnlineTraining.exceptions.UserNotFoundException;
import com.training.OnlineTraining.mapper.CoachMapper;
import com.training.OnlineTraining.mapper.CoachUserMapper;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.Role;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.model.UserRole;
import com.training.OnlineTraining.repository.CoachRepository;
import com.training.OnlineTraining.repository.RoleRepository;
import com.training.OnlineTraining.repository.UserRoleRepository;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.UserService;
import com.training.OnlineTraining.specification.CoachSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
    private final CoachMapper coachMapper;
    private final CoachUserMapper coachUserMapper;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void registerCoach(CoachDto coachDto, UUID userId) {
        Optional<User> optionalUser = Optional.ofNullable(userService.getUserById(userId));

        coachDto.setCoachUserDTO(coachUserMapper.toCoachUserDTO(optionalUser.get()));

        optionalUser.ifPresentOrElse(
                user -> {
                    coachRepository.save(coachMapper.coachDtoToCoach(coachDto));
                    Role roleCoach = roleRepository.findByName("COACH")
                            .orElseThrow(() -> new RuntimeException("Role not found: COACH"));

                    userRoleRepository.save(new UserRole(user, roleCoach));

                },
                () -> {
                    throw new UserNotFoundException(userId);
                }
        );
    }

    @Override
    public boolean isCoach(User user) { return coachRepository.existsByUser(user);
    }

    @Override
    public List<CoachDto> getAllCoaches() {
        return coachRepository.findAll().stream()
                .map(coachMapper::coachToCoachDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoachDto> filterCoaches(CoachFilterParams filterParams) {
        Specification<Coach> spec = Specification
                .where(CoachSpecifications.filterByGender(filterParams.getGender()))
                .and(CoachSpecifications.filterByExperience(filterParams.getExperience()))
                .and(CoachSpecifications.filterByAge(filterParams.getAge()))
                .and(CoachSpecifications.filterByEducation(filterParams.getEducation()))
                .and(CoachSpecifications.filterByMonthlyPrice(filterParams.getMonthlyPrice()));

        return coachRepository.findAll(spec).stream()
                .map(coachMapper::coachToCoachDto)
                .collect(Collectors.toList());
    }

    @Override
    public Double getMonthlyPriceById(UUID coachId) {
        return coachRepository.findMonthlyPriceById(coachId);
    }
}
