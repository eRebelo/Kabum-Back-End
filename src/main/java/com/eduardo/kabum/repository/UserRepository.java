package com.eduardo.kabum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.kabum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);

	User findByUsername(String username);
}