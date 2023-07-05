package net.queencoder.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.queencoder.springboot.auth.AuthenticationRequest;
import net.queencoder.springboot.auth.AuthenticationResponse;
import net.queencoder.springboot.auth.RegisterRequest;
import net.queencoder.springboot.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationService service;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(
		@RequestBody RegisterRequest request
	){
		return ResponseEntity.ok(service.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> register(
		@RequestBody AuthenticationRequest request
	){
		return ResponseEntity.ok(service.authenticate(request));
	}

}
