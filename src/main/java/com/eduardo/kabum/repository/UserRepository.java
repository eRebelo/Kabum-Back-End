package com.eduardo.kabum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eduardo.kabum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);

	@Query("select u from User u where u.name = :name and u.username = :username")
	User findByNameAndUsername(@Param("name") String name, @Param("username") String username);

}