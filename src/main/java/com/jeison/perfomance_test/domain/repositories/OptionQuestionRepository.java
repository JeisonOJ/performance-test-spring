package com.jeison.perfomance_test.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeison.perfomance_test.domain.entities.OptionQuestion;

@Repository
public interface OptionQuestionRepository extends JpaRepository<OptionQuestion,Long>{

}
