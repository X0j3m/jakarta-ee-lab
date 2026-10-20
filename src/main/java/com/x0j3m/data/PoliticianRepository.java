package com.x0j3m.data;

import com.x0j3m.config.AppInit;
import com.x0j3m.model.entity.PoliticianEntity;
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
@NoArgsConstructor
public class PoliticianRepository {

    @PersistenceContext(unitName = "H2PersistenceUnit")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        if (getAll().isEmpty()) {
            for (PoliticianEntity politician : AppInit.INIT_POLITICALIANS_LIST) {
                save(politician);
            }
        }
    }

    @Transactional
    public List<PoliticianEntity> getAll() {
        return entityManager.createQuery("SELECT pp FROM PoliticianEntity pp", PoliticianEntity.class).getResultList();
    }

    @Transactional
    public Optional<PoliticianEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(PoliticianEntity.class, id));
    }

    @Transactional
    public void save(PoliticianEntity p) {
        if (entityManager.find(PoliticianEntity.class, p.getId()) == null) {
            entityManager.persist(p);
        } else {
            entityManager.merge(p);
        }
    }

    @Transactional
    public void delete(UUID id) {
        entityManager.remove(entityManager.find(PoliticianEntity.class, id));
    }
}
