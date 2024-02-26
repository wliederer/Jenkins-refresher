package com.family.refresh.auth;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private String jwtSignKey = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(jwtSignKey).parseClaimsJws(token).getBody();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails);
	}

	public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
		return createToken(claims, userDetails);
	}

	private String createToken(Map<String, Object> claims, UserDetails userDetails) {
		System.out.println("Creating token " + userDetails.getUsername() + " "+ userDetails.getAuthorities());
//		String jwt = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername()).compact();
//		return jwt;
		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.claim("authorities", userDetails.getAuthorities()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
				.signWith(SignatureAlgorithm.HS256,jwtSignKey).compact();
	}
	
//	private Key getSignInKey() {
//	    byte[] keyBytes = Base64.getDecoder().decode(jwtSignKey);
//	    return new SecretKeySpec(keyBytes,"HmacSHA256");
//	  }

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
