package com.eduardo.kabum.resource;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardo.kabum.model.SystemUser;
import com.eduardo.kabum.repository.SystemUserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/kabum/api")
@Api(value = "User API REST")
public class SystemUserResource {

	@Autowired
	SystemUserRepository userRepository;

	BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

	@ApiOperation(value = "Returns a response if the credentials are correct")
	@GetMapping("/user/{username}/{password}")
	public ResponseEntity<SystemUser> login(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {

		SystemUser foundUser = userRepository.findByUsername(username);

		if (foundUser != null) {
			// Decrypt password
			if (pwdEncoder.matches(password, foundUser.getPassword())) {
				foundUser.setPassword(null);
				return new ResponseEntity<>(foundUser, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Save a user")
	@PostMapping("/user")
	public SystemUser userSave(@RequestBody @Valid SystemUser user) {

		// Crypt password
		user.setPassword(pwdEncoder.encode(user.getPassword()));

		// Set the creation date
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		user.setCreationDate(currentDateTime);

		// Insert new user
		SystemUser insertedUser = userRepository.save(user);
		insertedUser.setPassword(null);

		return insertedUser;
	}
}