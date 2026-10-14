package com.x0j3m.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "politicians")
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PoliticianEntity {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "constituency")
    private int constituency;
    @ManyToOne
    @JoinColumn(name = "political_party_id")
    private PoliticalPartyEntity politicalParty;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
