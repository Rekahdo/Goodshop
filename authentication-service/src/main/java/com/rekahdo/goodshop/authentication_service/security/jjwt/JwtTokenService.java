package com.rekahdo.goodshop.authentication_service.security.jjwt;

import com.rekahdo.goodshop.authentication_service.dtos.response.JwtResponse;
import com.rekahdo.goodshop.authentication_service.exceptions.classes.TokenExpiredException;
import com.rekahdo.goodshop.authentication_service.exceptions.classes.TokenValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

@Service
@Getter
@Slf4j
@SuppressWarnings("unchecked")
public class JwtTokenService {

    // Add this to expose the secret key for verification (DEV ONLY)
    private final String secretKey;
	private final Integer expireInHours = 48;

	public JwtTokenService() {
		// Generate key once during initialization
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey genSK = keyGen.generateKey();
			this.secretKey = Base64.getUrlEncoder().withoutPadding().encodeToString(genSK.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Failed to generate secret key", e);
		}
	}

	public JwtResponse generate(String subject, List<String> roles, Map<String, ?> claims, Integer expireInHours) {
		long oneHour = 60 * 60;
		String token = Jwts.builder()
				.claims()
				.subject(subject)
				.issuedAt(Date.from(Instant.now()))
				.expiration(Date.from(Instant.now().plusSeconds(oneHour * expireInHours)))
				.issuer("self")
				.add(Map.of("roles", roles))
				.add(Map.of("other-claims", claims))
				.and().signWith(getKey()).compact();

		return new JwtResponse(token, getSecretKey(), expireInHours);
	}

	// Contains the Symmetric key for encoding and decoding the token
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	// Returns all the Claims in the token
	private Claims getClaims(String token){
		try{
			return Jwts.parser()
					.verifyWith(getKey())
					.build()
					.parseSignedClaims(token)
					.getPayload();
		} catch (ExpiredJwtException ex){
			throw new TokenExpiredException();
		} catch (JwtException | IllegalArgumentException ex){
			throw new TokenValidationException();
		}
	}

	// Returns a specific claim from the claims in a token
	private <T> T getClaim(String token, String key, Class<T> cls) {
		final Claims claims = getClaims(token);
		return claims.get(key, cls);
	}

	public String getSubject(String token) {
		return getClaims(token).getSubject();
	}

	public List<String> getRoles(String token) {
		return getClaim(token, "roles", List.class);
	}

	public Map<String, ?> getOtherClaims(String token) {
		return getClaim(token, "other-claims", Map.class);
	}

	private Date getExpiration(String token) {
		return getClaims(token).getExpiration();
	}

	public boolean isTokenExpired(String token) {
		return new Date().after(getExpiration(token));
	}

	public boolean isTokenValid(String token, String name) {
		final String tokenName = getSubject(token);
		return (tokenName.equals(name) && !isTokenExpired(token));
	}

}