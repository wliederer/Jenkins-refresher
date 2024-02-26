package com.family.refresh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.family.refresh.auth.JwtUtils;
import com.family.refresh.models.AuthenticationRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtUtils jwtUtils;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
		logger.info("In Authentication Controller " + request.getEmail() + request.getPassword());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
				);
		logger.info("After authenticationManager");
		final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
		logger.info("User details "+ user);
		if(user != null) {
			String token = jwtUtils.generateToken(user);
			logger.info("Created token " + token );
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.status(400).body("Some error has occured");
	}

}
