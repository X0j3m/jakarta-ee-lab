package com.x0j3m.model.mapper;

import com.x0j3m.model.dto.user.UserRequestDto;
import com.x0j3m.model.dto.user.UserResponseDto;
import com.x0j3m.model.entity.Role;
import com.x0j3m.model.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor
public class UserMapper {
    public UserEntity toEntity(UUID id, UserRequestDto userDto) {
        return UserEntity.builder()
                .id(id)
                .username(userDto.username())
                .birthDate(LocalDate.parse(userDto.birthDate()))
                .role(Role.valueOf(userDto.role()))
                .build();
    }

    public UserResponseDto toDto(UserEntity userEntity) {
        return UserResponseDto.builder()
                .id(userEntity.getId().toString())
                .username(userEntity.getUsername())
                .birthDate(String.valueOf(userEntity.getBirthDate()))
                .role(userEntity.getRole().name())
                .build();
    }
}
