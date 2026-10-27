package com.x0j3m.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "political_parties")
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PoliticalPartyEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "foundation_date")
    private LocalDate foundationDate;
    @Column(name = "orientation")
    private Orientation orientation;
    @OneToMany(mappedBy = "politicalParty", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PoliticianEntity> politicians;
}
