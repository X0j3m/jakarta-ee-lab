package com.x0j3m.data;

import com.x0j3m.model.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private final Map<UUID, UserEntity> usersStorage = new ConcurrentHashMap<>();

    public List<UserEntity> getAll() {
        return List.copyOf(usersStorage.values());
    }

    public Optional<UserEntity> getById(UUID id) {
        return Optional.ofNullable(usersStorage.get(id));
    }

    public void save(UserEntity user) {
        usersStorage.put(user.getId(), user);
    }

    public void delete(UUID userId) {
        usersStorage.remove(userId);
    }
}
