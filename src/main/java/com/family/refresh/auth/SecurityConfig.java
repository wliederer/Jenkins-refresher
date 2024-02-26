package com.family.refresh.auth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;
	private final UserDetailsService userDetailsService;
	private final AuthenticationProvider authenticationProvider;

	@Bean
	@Order(1)
	SecurityFilterChain friendsFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/friends/**").authorizeHttpRequests(auth -> {
//					auth.requestMatchers(AntPathRequestMatcher.antMatcher("/friends/**")).authenticated();
			auth.anyRequest().authenticated();
		}).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(Customizer.withDefaults()).build();
	}

	@Bean
	@Order(2)
	SecurityFilterChain addressFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher(AntPathRequestMatcher.antMatcher("/address/**")).authorizeHttpRequests(auth -> {
			auth.requestMatchers(AntPathRequestMatcher.antMatcher("/address/**")).permitAll();
		}).csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/address/**")))
				.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()).build();
	}

	@Bean
	@Order(3)
	public SecurityFilterChain parentsFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/parents/**").authorizeHttpRequests(auth -> {
			auth.anyRequest().authenticated();
		})
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider)
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	@Order(4)
	public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
		return http.securityMatcher("/auth/**").authorizeHttpRequests(auth -> {
			auth.anyRequest().permitAll();
		}).csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/auth/**")))
				.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
			.build();
	}


	@Bean
	@Order(5)
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/").permitAll();
			auth.requestMatchers("/error").permitAll();
			auth.anyRequest().authenticated();
		}).formLogin(Customizer.withDefaults()).build();
	}


}
