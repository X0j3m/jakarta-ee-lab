package com.x0j3m.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "political_parties")
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class PoliticalPartyEntity {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "foundation_date")
    private LocalDate foundationDate;
    @Column(name = "orientation")
    private Orientation orientation;
    @OneToMany(mappedBy = "politicalParty")
    private List<PoliticianEntity> politicians;
}
