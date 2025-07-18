package com.rekahdo.ecommerce.goodshop._entities;

import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import com.rekahdo.ecommerce.goodshop.utilities.StringFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true)
	private String email;

	private Instant createdAt;
	private Instant updatedAt;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<Authority>();

	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Profile profile;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

	// HELPER CONSTRUCTORS
	public AppUser(Long id, String username, String password, String email, Instant createdAt) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdAt = createdAt;
	}

	public AppUser(Long id, String username) {
		this.id = id;
		this.username = username;
	}

	// GETTERS AND SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	// HELPER METHODS
	public void addAuthoritiesByRoles(AuthorityRole... roles) {
		List<Authority> newAuthorities = Arrays.stream(roles)
				.map(role -> new Authority(role, this))
				.toList();
		authorities.addAll(newAuthorities);
	}

	public void addAuthoritiesByRole(AuthorityRole role) {
		addAuthoritiesByRoles(role);
	}

	public void removeAuthoritiesByRoles(AuthorityRole... roles) {
		List<AuthorityRole> rolesList = Arrays.asList(roles);
		List<Authority> removeAuthorities = authorities.stream()
				.filter(authority -> rolesList.contains(authority.getRole()))
				.toList();
		authorities.removeAll(removeAuthorities);
	}

	public void removeAuthoritiesByRole(AuthorityRole role) {
		removeAuthoritiesByRoles(role);
	}

	public String fetchAuthoritiesAsRoles() {
		String[] array = authorities.stream()
				.map(authority -> authority.getRole().getValue())
				.toArray(String[]::new);
		return StringFormat.join(array);
	}

	public Collection<? extends GrantedAuthority> fetchAuthoritiesAsGrantedAuthorities() {
		return authorities.stream().map(authority -> {
			String role = authority.getRole().getValue();
			return new SimpleGrantedAuthority(role.startsWith("ROLE_") ? role : "ROLE_" + role);
		}).toList();
	}

}
