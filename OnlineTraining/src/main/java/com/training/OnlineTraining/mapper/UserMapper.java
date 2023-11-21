package com.training.OnlineTraining.mapper;

import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.util.PasswordUtils;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Named("hashPassword")
    default String hashPassword(String plainPassword) {
        return PasswordUtils.hashPassword(plainPassword);
    }
    void mapFieldsWithoutHashing(UserDto dto, @MappingTarget User user);
    default boolean isStringNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    default boolean isAgeInvalid(Integer age) {
        return age == null || age <= 0;
    }
}
