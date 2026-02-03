package com.rekahdo.goodshop.user_service.security.config;

import com.rekahdo.goodshop.user_service.entities.AppUser;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class AppUserDetails implements UserDetails {

	private final Long id;
	private final String email;
	private final List<String> roles;

	@Getter(AccessLevel.NONE)
	private final AppUser user;

	public AppUserDetails(AppUser user, List<String> roles) {
		this.user = user;
		this.id = user.getId();
		this.email = user.getEmail();
		this.roles = roles.isEmpty() ? List.of("ROLE_USER") :
				roles.stream().map(role -> String.format("ROLE_%s", role)).toList();
	}

    @Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(SimpleGrantedAuthority::new).toList();
	}

	@Override
	public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

	@Override
	public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

	@Override
	public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

	@Override
	public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}