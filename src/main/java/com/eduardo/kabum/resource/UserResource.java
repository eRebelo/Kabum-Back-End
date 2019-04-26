package com.eduardo.kabum.resource;

import java.util.Map;

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

import com.eduardo.kabum.model.User;
import com.eduardo.kabum.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/kabum/api")
@Api(value = "User API REST")
public class UserResource {

	@Autowired
	UserRepository userRepository;

	BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

	// @ApiOperation(value = "Return a user list")
	// @GetMapping("/user")
	// public List<User> userList() {
	// return (List<User>) userRepository.findAll();
	// }

	// @ApiOperation(value = "Return a user by id")
	// @GetMapping("/user/{id}")
	// public User userById(@PathVariable(value = "id") long id) {
	// return userRepository.findById(id);
	// }

	@ApiOperation(value = "Return a user by name and username")
	@GetMapping("/user/{username}/{password}")
	public ResponseEntity<User> login(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password) {

		User foundUser = userRepository.findByUsername(username);

		if (foundUser != null) {
			// Decrypt password
			if (pwdEncoder.matches(password, foundUser.getPassword())) {
				foundUser.setPassword(null);
				return new ResponseEntity<>(foundUser, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Save a user")
	@PostMapping("/user")
	public User userSave(@RequestBody @Valid User user) {

		// Crypt password
		user.setPassword(pwdEncoder.encode(user.getPassword()));

		// Insert new user
		User insertedUser = userRepository.save(user);
		insertedUser.setPassword(null);

		return insertedUser;
	}

}
