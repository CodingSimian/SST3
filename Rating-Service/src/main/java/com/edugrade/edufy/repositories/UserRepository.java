package com.edugrade.edufy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edugrade.edufy.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUserId(Long userId);

}
