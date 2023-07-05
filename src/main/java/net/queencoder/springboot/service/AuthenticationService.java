package net.queencoder.springboot.service;

import net.queencoder.springboot.auth.AuthenticationRequest;
import net.queencoder.springboot.auth.AuthenticationResponse;
import net.queencoder.springboot.auth.RegisterRequest;

public interface AuthenticationService {
	public AuthenticationResponse register(RegisterRequest request);
	public AuthenticationResponse authenticate(AuthenticationRequest request);
}
