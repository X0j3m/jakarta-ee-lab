package com.x0j3m.data;

import com.x0j3m.config.AppInit;
import com.x0j3m.model.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@NoArgsConstructor(force = true)
public class UserRepository {

    @PersistenceContext(unitName = "H2PersistenceUnit")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        if (getAll().isEmpty()) {
            for (UserEntity user : AppInit.INIT_USERS_LIST) {
                save(user);
            }
        }
    }

    @Transactional
    public List<UserEntity> getAll() {
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }

    @Transactional
    public Optional<UserEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    @Transactional
    public void save(UserEntity user) {
        if (entityManager.find(UserEntity.class, user.getId()) == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Transactional
    public void delete(UUID id) {
        entityManager.remove(entityManager.find(UserEntity.class, id));
    }
}
