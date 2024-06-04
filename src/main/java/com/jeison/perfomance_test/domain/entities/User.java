package com.jeison.perfomance_test.domain.entities;

import com.jeison.perfomance_test.utils.enums.RoleUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String userName;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(length = 100)
    private String fullName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUser roleUser;
}
