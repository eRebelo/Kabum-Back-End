package com.eduardo.kabum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.kabum.model.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

	SystemUser findById(long id);

	SystemUser findByUsername(String username);
}