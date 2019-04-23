package com.eduardo.kabum.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@ApiOperation(value = "Return a user list")
	@GetMapping("/user")
	public List<User> userList() {
		return (List<User>) userRepository.findAll();
	}

	@ApiOperation(value = "Return a user by id")
	@GetMapping("/user/{id}")
	public User userById(@PathVariable(value = "id") long id) {
		return userRepository.findById(id);
	}

	@ApiOperation(value = "Return a user by name and username")
	@GetMapping("/user/{name}/{username}")
	public User userByNameAndUsername(@PathVariable(value = "name") String name, @PathVariable(value = "username") String username) {

		User tempUser = userRepository.findByNameAndUsername(name, username);
		if (tempUser != null) {
			tempUser.setPassword("user found");
		}

		return tempUser;
	}

	@ApiOperation(value = "Save a user")
	@PostMapping("/user")
	public User userSave(@RequestBody @Valid User user) {
		return userRepository.save(user);
	}

}
