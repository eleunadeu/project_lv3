package com.sparta.office.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String team;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AdminRoleEnum role;

    public Admin(String email, String password, String team, AdminRoleEnum role) {
        this.email = email;
        this.password = password;
        this.team = team;
        this.role = role;
    }
}
