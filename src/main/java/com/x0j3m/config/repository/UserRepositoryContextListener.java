package com.x0j3m.config.repository;

import com.x0j3m.data.UserRepository;
import com.x0j3m.model.entity.Role;
import com.x0j3m.model.entity.UserEntity;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.List;
import java.util.UUID;

@WebListener
public class UserRepositoryContextListener implements ServletContextListener {

    private static final List<UserEntity> INIT_USERS_LIST = List.of(
            UserEntity.builder()
                    .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                    .username("admin")
                    .role(Role.ADMIN)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                    .username("user1")
                    .role(Role.USER)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                    .username("user2")
                    .role(Role.USER)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("44444444-4444-4444-4444-444444444444"))
                    .username("user3")
                    .role(Role.USER)
                    .build(),
            UserEntity.builder()
                    .id(UUID.fromString("55555555-5555-5555-5555-555555555555"))
                    .username("user4")
                    .role(Role.USER)
                    .build()
    );

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserRepository repository = new UserRepository();

        for (UserEntity user : INIT_USERS_LIST) {
            repository.save(user);
        }

        sce.getServletContext().setAttribute(UserRepository.class.getName(), repository);
    }
}
