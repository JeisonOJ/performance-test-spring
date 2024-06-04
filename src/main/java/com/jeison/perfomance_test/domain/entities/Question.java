package com.jeison.perfomance_test.domain.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String text;
    @Column(nullable = false, length = 50)
    private String type;
    @Column(nullable = false)
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "survey_id",
        referencedColumnName = "id"
    )
    private Survey survey;
    @OneToMany(mappedBy = "question",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    orphanRemoval = false)
    private List<OptionQuestion> options;
}
