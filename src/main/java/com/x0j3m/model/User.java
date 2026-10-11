package com.x0j3m.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class User {
    @Id
    private UUID id;
    private String username;
    private Role role;
    private List<Politician> politicians;
}
