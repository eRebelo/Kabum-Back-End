package com.eduardo.kabum.resource;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.kabum.model.Customer;
import com.eduardo.kabum.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/kabum/api")
@Api(value = "Customer API REST")
public class CustomerResource {

	@Autowired
	CustomerRepository customerRepository;

	@ApiOperation(value = "Return a customer list")
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> customerList() {

		List<Customer> foundCustomers = customerRepository.findAll();

		if (foundCustomers != null) {
			return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Return a customer by id")
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> customerById(@PathVariable(value = "id") long id) {
		Customer foundCustomer = customerRepository.findById(id);

		if (foundCustomer != null) {
			return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Save a customer")
	@PostMapping("/customer")
	public Customer customerSave(@RequestBody @Valid Customer customer) {

		// Set the creation and change date
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		customer.setCreationDate(currentDateTime);
		customer.setChangeDate(currentDateTime);

		return customerRepository.save(customer);
	}

	@ApiOperation(value = "Update a customer")
	@PutMapping("/customer")
	public Customer customerUpdate(@RequestBody @Valid Customer customer) {

		// Updating the change date
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		customer.setChangeDate(currentDateTime);

		return customerRepository.save(customer);
	}

	@ApiOperation(value = "Delete a customer by id")
	@DeleteMapping("/customer/{id}")
	public void customerDelete(@PathVariable(value = "id") long id) {
		customerRepository.delete(customerRepository.findById(id));
	}
}