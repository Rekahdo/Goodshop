package com.rekahdo.ecommerce.goodshop._entities;

import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import com.rekahdo.ecommerce.goodshop.utilities.StringFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

	private LocalDate createdAt;
	private LocalDate updatedAt;

	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Authority authority;

	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Profile profile;

	@OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> addresses = new ArrayList<>();

	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private ForgotPassword forgotPassword;

	// HELPER CONSTRUCTORS
	public AppUser(String username, String password, String email, LocalDate createdAt) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdAt = createdAt;
	}

	public AppUser(Long id) {
		this.id = id;
	}

}
