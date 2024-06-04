package com.jeison.perfomance_test.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    @Column(nullable = false, length = 100)
    private String userName;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private Boolean isActive;
    @OneToMany(mappedBy = "user",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = false)
    private List<Survey> surveys;

}
