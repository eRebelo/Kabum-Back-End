package com.eduardo.kabum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.kabum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);

	/*
	 * @Query("select u from User u where u.name = :username and u.password = :password"
	 * ) User findByUsernameAndPass(@Param("username") String
	 * username, @Param("password") String password);
	 */

	User findByUsername(String username);

}