package com.jeison.perfomance_test.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeison.perfomance_test.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
