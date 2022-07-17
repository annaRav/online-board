package com.api.onlineboard.service.mapper;

import com.api.onlineboard.dto.UserRequestDto;
import com.api.onlineboard.dto.UserResponseDto;
import com.api.onlineboard.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User toModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setBirthDate(dto.getBirthDate());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        //todo other fields
        return user;
    }
}
