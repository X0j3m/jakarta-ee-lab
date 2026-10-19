package com.x0j3m.config;

import com.x0j3m.model.entity.Role;
import com.x0j3m.model.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class AppInit {
    public static final List<UserEntity> INIT_USERS_LIST = List.of(
            UserEntity.builder()
                    .id(UUID.fromString("11111111-2222-3333-4444-555555555555"))
                    .username("admin")
                    .birthDate(LocalDate.of(2000, 12, 12))
                    .role(Role.ADMIN)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                    .username("user1")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .role(Role.USER)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                    .username("user2")
                    .birthDate(LocalDate.of(2002, 2, 2))
                    .role(Role.USER)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                    .username("user3")
                    .birthDate(LocalDate.of(2003, 3, 3))
                    .role(Role.USER)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("44444444-4444-4444-4444-444444444444"))
                    .username("user4")
                    .birthDate(LocalDate.of(2004, 4, 4))
                    .role(Role.USER)
                    .build()
    );
}
