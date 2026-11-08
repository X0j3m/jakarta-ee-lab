package com.x0j3m.config;

import com.x0j3m.data.PoliticalPartyRepository;
import com.x0j3m.data.PoliticianRepository;
import com.x0j3m.data.UserRepository;
import com.x0j3m.model.entity.PoliticalPartyEntity;
import com.x0j3m.model.entity.PoliticianEntity;
import com.x0j3m.model.entity.UserEntity;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Singleton
@Startup
public class DbInitializer {
    @PersistenceContext(unitName = "H2PersistenceUnit")
    private EntityManager entityManager;

    @Inject
    private PoliticalPartyRepository politicalPartyRepository;
    @Inject
    private PoliticianRepository politicianRepository;
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void init() {
        boolean usersEmpty = userRepository.getAll().isEmpty();
        boolean partiesEmpty = politicalPartyRepository.getAll().isEmpty();
        boolean politiciansEmpty = politicianRepository.getAll().isEmpty();

        if (usersEmpty) {
            for (UserEntity user : AppInit.INIT_USERS_LIST) {
                userRepository.save(user);
            }
        }

        if (partiesEmpty) {
            for (PoliticalPartyEntity politicalParty : AppInit.INIT_POLITICAL_PARTIES_LIST) {
                politicalPartyRepository.save(politicalParty);
            }
        }

        if (politiciansEmpty) {
            for (PoliticianEntity politician : AppInit.INIT_POLITICALIANS_LIST) {
                politicianRepository.save(politician);
            }
        }

        entityManager.flush();
        entityManager.clear();
    }
}