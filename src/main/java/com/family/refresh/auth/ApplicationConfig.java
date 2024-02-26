package com.family.refresh.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	private final static List<UserDetails> USERS = Arrays.asList(
			new User("user",passwordEncoder().encode("password"),Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))
			);
	
	@Bean
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				return USERS.stream().filter(u->u.getUsername()
						.equals(email)).findFirst()
						.orElseThrow(()->new UsernameNotFoundException("User not found"));
			}
		};
//		var user = User.builder().username("user").password(passwordEncoder().encode("password")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
