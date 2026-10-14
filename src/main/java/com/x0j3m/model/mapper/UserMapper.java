package com.x0j3m.model.mapper;

import com.x0j3m.model.dto.UserRequestDto;
import com.x0j3m.model.dto.UserResponseDto;
import com.x0j3m.model.entity.Role;
import com.x0j3m.model.entity.UserEntity;

import java.util.UUID;

public class UserMapper {
    public UserEntity toEntity(UUID id, UserRequestDto userDto) {
        return UserEntity.builder()
                .id(id)
                .username(userDto.username())
                .age(Integer.parseInt(userDto.age()))
                .role(Role.valueOf(userDto.role()))
                .build();
    }

    public UserResponseDto toDto(UserEntity userEntity) {
        return UserResponseDto.builder()
                .id(userEntity.getId().toString())
                .username(userEntity.getUsername())
                .age(String.valueOf(userEntity.getAge()))
                .role(userEntity.getRole().name())
                .build();
    }
}
