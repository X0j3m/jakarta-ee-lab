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

@Singleton
@Startup
public class DbInitializer {
    @Inject
    private PoliticalPartyRepository politicalPartyRepository;
    @Inject
    private PoliticianRepository politicianRepository;
    @Inject
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (politicalPartyRepository.getAll().isEmpty()) {
            for (PoliticalPartyEntity politicalParty : AppInit.INIT_POLITICAL_PARTIES_LIST) {
                politicalPartyRepository.save(politicalParty);
            }
        }

        if (politicianRepository.getAll().isEmpty()) {
            for (PoliticianEntity politician : AppInit.INIT_POLITICALIANS_LIST) {
                politicianRepository.save(politician);
            }
        }

        if (userRepository.getAll().isEmpty()) {
            for (UserEntity user : AppInit.INIT_USERS_LIST) {
                userRepository.save(user);
            }
        }
    }
}
