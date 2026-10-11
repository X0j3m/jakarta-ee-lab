package com.x0j3m.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class PoliticalParty {
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "foundation_date")
    private LocalDate foundationDate;
    @Column(name = "orientation")
    private Orientation orientation;
    private List<Politician> politicians;
}
