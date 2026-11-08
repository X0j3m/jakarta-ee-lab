package com.x0j3m.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "politicians")
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PoliticianEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "constituency")
    private int constituency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "political_party_id")
    private PoliticalPartyEntity politicalParty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public String toString() {
        return "PoliticianEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", constituency=" + constituency +
                ", politicalPartyId=" + (politicalParty != null ? politicalParty.getId() : null) +
                ", userId=" + (user != null ? user.getId() : null) +
                '}';
    }
}