package com.x0j3m.model.entity;

import jakarta.persistence.*;
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
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column (name = "age")
    private int age;
    @Column(name = "role")
    private Role role;
    @OneToMany(mappedBy = "user")
    private List<PoliticianEntity> politicians;
}
