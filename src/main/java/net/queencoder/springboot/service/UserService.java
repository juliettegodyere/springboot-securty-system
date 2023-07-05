package net.queencoder.springboot.service;

import java.util.List;

import net.queencoder.springboot.auth.AuthenticationRequest;
import net.queencoder.springboot.auth.AuthenticationResponse;
import net.queencoder.springboot.auth.RegisterRequest;
import net.queencoder.springboot.model.Role;
import net.queencoder.springboot.model.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();
}
