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
                    .name("Jan")
                    .surname("Kowalski")
                    .birthDate(LocalDate.of(1982, 3, 15))
                    .constituency(15)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(0))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("11111111-AAAA-1111-AAAA-222222222222"))
                    .name("Piotr")
                    .surname("Nowak")
                    .birthDate(LocalDate.of(1975, 7, 22))
                    .constituency(1)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(0))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("11111111-AAAA-1111-AAAA-333333333333"))
                    .name("Katarzyna")
                    .surname("Wiśniewska")
                    .birthDate(LocalDate.of(1990, 11, 5))
                    .constituency(9)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(0))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("11111111-AAAA-1111-AAAA-444444444444"))
                    .name("Tomasz")
                    .surname("Wójcik")
                    .birthDate(LocalDate.of(1968, 1, 30))
                    .constituency(3)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(0))
                    .build(),

            PoliticianEntity.builder()
                    .id(UUID.fromString("22222222-BBBB-2222-BBBB-222222222222"))
                    .name("Michał")
                    .surname("Kamiński")
                    .birthDate(LocalDate.of(1988, 4, 18))
                    .constituency(5)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(1))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("22222222-BBBB-2222-BBBB-333333333333"))
                    .name("Anna")
                    .surname("Zielińska")
                    .birthDate(LocalDate.of(1993, 9, 12))
                    .constituency(7)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(1))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("22222222-BBBB-2222-BBBB-444444444444"))
                    .name("Krzysztof")
                    .surname("Szymański")
                    .birthDate(LocalDate.of(1979, 6, 25))
                    .constituency(21)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(1))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("22222222-BBBB-2222-BBBB-555555555555"))
                    .name("Agnieszka")
                    .surname("Woźniak")
                    .birthDate(LocalDate.of(1985, 12, 3))
                    .constituency(2)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(1))
                    .build(),

            PoliticianEntity.builder()
                    .id(UUID.fromString("33333333-CCCC-3333-CCCC-333333333333"))
                    .name("Paweł")
                    .surname("Dąbrowski")
                    .birthDate(LocalDate.of(1972, 8, 14))
                    .constituency(4)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(2))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("33333333-CCCC-3333-CCCC-444444444444"))
                    .name("Magdalena")
                    .surname("Kozłowska")
                    .birthDate(LocalDate.of(1991, 2, 28))
                    .constituency(18)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(2))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("33333333-CCCC-3333-CCCC-555555555555"))
                    .name("Marcin")
                    .surname("Jankowski")
                    .birthDate(LocalDate.of(1980, 10, 19))
                    .constituency(7)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(2))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("33333333-CCCC-3333-CCCC-666666666666"))
                    .name("Monika")
                    .surname("Mazur")
                    .birthDate(LocalDate.of(1987, 5, 8))
                    .constituency(11)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(2))
                    .build(),

            PoliticianEntity.builder()
                    .id(UUID.fromString("44444444-DDDD-4444-DDDD-444444444444"))
                    .name("Grzegorz")
                    .surname("Krawczyk")
                    .birthDate(LocalDate.of(1976, 11, 21))
                    .constituency(10)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(3))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("44444444-DDDD-4444-DDDD-555555555555"))
                    .name("Ewa")
                    .surname("Piotrowska")
                    .birthDate(LocalDate.of(1984, 7, 9))
                    .constituency(9)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(3))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("44444444-DDDD-4444-DDDD-666666666666"))
                    .name("Łukasz")
                    .surname("Grabowski")
                    .birthDate(LocalDate.of(1995, 4, 2))
                    .constituency(18)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(3))
                    .build(),
            PoliticianEntity.builder()
                    .id(UUID.fromString("44444444-DDDD-4444-DDDD-777777777777"))
                    .name("Karolina")
                    .surname("Pawłowska")
                    .birthDate(LocalDate.of(1989, 9, 17))
                    .constituency(7)
                    .politicalParty(INIT_POLITICAL_PARTIES_LIST.get(3))
                    .build()
    );
}
