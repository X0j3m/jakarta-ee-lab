package com.x0j3m.data;

import com.x0j3m.model.entity.PoliticalPartyEntity;
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
public class PoliticalPartyRepository {

    @PersistenceContext(unitName = "H2PersistenceUnit")
    private EntityManager entityManager;

    @Transactional
    public List<PoliticalPartyEntity> getAll() {
        return entityManager.createQuery("SELECT pp FROM PoliticalPartyEntity pp", PoliticalPartyEntity.class).getResultList();
    }

    @Transactional
    public Optional<PoliticalPartyEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(PoliticalPartyEntity.class, id));
    }

    @Transactional
    public void save(PoliticalPartyEntity pp) {
        if (entityManager.find(PoliticalPartyEntity.class, pp.getId()) == null) {
            entityManager.persist(pp);
        } else {
            entityManager.merge(pp);
        }
    }

    @Transactional
    public void delete(UUID id) {
        entityManager.remove(entityManager.find(PoliticalPartyEntity.class, id));
    }
}
