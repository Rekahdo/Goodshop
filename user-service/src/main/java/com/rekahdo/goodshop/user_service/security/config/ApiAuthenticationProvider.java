package com.rekahdo.goodshop.user_service.security.config;

import com.rekahdo.goodshop.user_service.exceptions.classes.IncorrectPasswordException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApiAuthenticationProvider implements AuthenticationProvider {

	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	public ApiAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String enteredPassword = authentication.getCredentials().toString();

		AppUserDetails user = (AppUserDetails) userDetailsService.loadUserByUsername(username);
		if (passwordEncoder.matches(enteredPassword, user.getPassword()))
			return new UsernamePasswordAuthenticationToken(
					user, null, user.getAuthorities());

		throw new IncorrectPasswordException(enteredPassword);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}