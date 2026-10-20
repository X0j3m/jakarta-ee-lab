package com.x0j3m.config;

import com.x0j3m.model.entity.*;

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

    public static final List<PoliticalPartyEntity> INIT_POLITICAL_PARTIES_LIST = List.of(
            PoliticalPartyEntity.builder()
                    .id(UUID.fromString("AAAAAAAA-AAAA-AAAA-AAAA-AAAAAAAAAAAA"))
                    .name("Partia 1")
                    .foundationDate(LocalDate.of(2001, 1, 1))
                    .orientation(Orientation.RIGHT)
                    .build(),
            PoliticalPartyEntity.builder()
                    .id(UUID.fromString("BBBBBBBB-BBBB-BBBB-BBBB-BBBBBBBBBBBB"))
                    .name("Partia 2")
                    .foundationDate(LocalDate.of(2002, 2, 2))
                    .orientation(Orientation.LEFT)
                    .build(),
            PoliticalPartyEntity.builder()
                    .id(UUID.fromString("CCCCCCCC-CCCC-CCCC-CCCC-CCCCCCCCCCCC"))
                    .name("Partia 3")
                    .foundationDate(LocalDate.of(2003, 3, 3))
                    .orientation(Orientation.CENTER)
                    .build(),
            PoliticalPartyEntity.builder()
                    .id(UUID.fromString("DDDDDDDD-DDDD-DDDD-DDDD-DDDDDDDDDDDD"))
                    .name("Partia 4")
                    .foundationDate(LocalDate.of(2004, 4, 4))
                    .orientation(Orientation.CENTER)
                    .build()
    );

    public static final List<PoliticianEntity> INIT_POLITICALIANS_LIST = List.of(
            PoliticianEntity.builder()
                    .id(UUID.fromString("11111111-AAAA-1111-AAAA-111111111111"))
                    .name("Imie 1")
                    .surname("Nazwisko 1")
                    .birthDate(LocalDate.of(2001, 1, 1))
                    .constituency(1)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(0))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("22222222-BBBB-2222-BBBB-222222222222"))
                    .name("Imie 2")
                    .surname("Nazwisko 2")
                    .birthDate(LocalDate.of(2002, 2, 2))
                    .constituency(2)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(1))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("33333333-CCCC-3333-CCCC-333333333333"))
                    .name("Imie 3")
                    .surname("Nazwisko 3")
                    .birthDate(LocalDate.of(2003, 3, 3))
                    .constituency(3)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(2))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("44444444-DDDD-4444-DDDD-444444444444"))
                    .name("Imie 4")
                    .surname("Nazwisko 4")
                    .birthDate(LocalDate.of(2004, 4, 4))
                    .constituency(4)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(3))
                    .build()
    );
}
