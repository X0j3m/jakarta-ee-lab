package com.x0j3m.service;

import com.x0j3m.data.UserRepository;
import com.x0j3m.model.dto.user.UserRequestDto;
import com.x0j3m.model.dto.user.UserResponseDto;
import com.x0j3m.model.entity.UserEntity;
import com.x0j3m.model.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserService {
    @Inject
    private UserRepository userRepository;
    @Inject
    private UserMapper userMapper;

    public List<UserResponseDto> fetchAll() {
        return userRepository.getAll()
                .stream()
                .map(userMapper::toDto)
                .toList();

    }

    public UserResponseDto fetchUserById(UUID id) {
        Optional<UserEntity> user = userRepository.getById(id);
        return user.map(userMapper::toDto).orElseThrow();
    }

    public String createUser(UserRequestDto userDto) {
        UserEntity user = userMapper.toEntity(UUID.randomUUID(), userDto);
        userRepository.save(user);
        return user.getId().toString();
    }

    public void updateUserById(UUID id, UserRequestDto userDto) {
        UserEntity updatedUser = userMapper.toEntity(id, userDto);
        userRepository.save(updatedUser);
    }

    public void deleteUserById(UUID id) {
        userRepository.delete(id);
    }

    public boolean existsById(UUID id) {
        return userRepository.getById(id).isPresent();
    }
}
