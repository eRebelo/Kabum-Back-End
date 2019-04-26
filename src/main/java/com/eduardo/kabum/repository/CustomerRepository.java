package com.eduardo.kabum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.kabum.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findById(long id);

}