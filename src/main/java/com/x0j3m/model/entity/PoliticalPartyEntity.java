package com.x0j3m.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "political_parties")
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PoliticalPartyEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "foundation_date")
    private LocalDate foundationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "orientation")
    private Orientation orientation;
    @Builder.Default
    @OneToMany(mappedBy = "politicalParty", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PoliticianEntity> politicians = new ArrayList<>();

    @Override
    public String toString() {
        return "PoliticalPartyEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundationDate=" + foundationDate +
                ", orientation=" + orientation +
                '}';
    }
}