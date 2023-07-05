package net.queencoder.springboot.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.queencoder.springboot.auth.AuthenticationRequest;
import net.queencoder.springboot.auth.AuthenticationResponse;
import net.queencoder.springboot.auth.RegisterRequest;
import net.queencoder.springboot.config.JwtService;
import net.queencoder.springboot.repository.UserRepository;
import net.queencoder.springboot.model.Role;
import net.queencoder.springboot.model.User;

@Service
@AllArgsConstructor
public class AuthenticationServiceimpl implements  AuthenticationService{
	
	private UserRepository repository;
	
	private PasswordEncoder passwordEncoder;
	
	private JwtService jwtService;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		var user = User.builder()
			.firstName(request.getFirstName())
			.lastName(request.getLastName())
			.email(request.getEmail())
			.password(passwordEncoder.encode(request.getPassword()))
			.role(Role.USER)
			.build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()
			)
		);
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

}

