package com.rekahdo.goodshop.user_service.security.jjwt;

import com.rekahdo.goodshop.user_service.feign.clients.AuthenticationServiceClient;
import com.rekahdo.goodshop.user_service.utilities.ApiKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Lazy
@SuppressWarnings("unchecked")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final AuthenticationServiceClient authenticationService;

	@Autowired
	public JwtAuthenticationFilter(AuthenticationServiceClient authenticationService) {
		this.authenticationService = authenticationService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = getTokenFromRequest(request);

		if(StringUtils.hasText(token)) {
			ModelMap modelMap = authenticationService.tokenInfo(token, ApiKey.AUTH_SERVICE);
			JwtUserDetails userDetails = new JwtUserDetails(
					Long.parseLong(modelMap.get("userId").toString()), // userId
					modelMap.get("username").toString(), // username
					modelMap.get("email").toString(), // email
					(List<String>) modelMap.get("roles") // roles
			);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

        filterChain.doFilter(request, response);
	}

	// Return the token from the HttpServletRequest object
	private String getTokenFromRequest(HttpServletRequest request){
		String authHeader = request.getHeader("Authorization");

		// authHeader would contain "Bearer encoded-token"
		if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
			return authHeader.substring(7);

		return null;
	}

	@Getter
	public static class JwtUserDetails extends User {

		private final long userId;
		private final String email;

		public JwtUserDetails(long userId, String username, String email, List<String> roles) {
			super(username, "", roles.stream().map(SimpleGrantedAuthority::new).toList());
			this.userId = userId;
			this.email = email;
		}

	}

}