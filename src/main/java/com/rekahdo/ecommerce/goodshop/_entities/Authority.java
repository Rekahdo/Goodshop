package com.rekahdo.ecommerce.goodshop._entities;

import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	@Enumerated(EnumType.STRING)
	private AuthorityRole role;

	@OneToOne
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	private LocalDate assignedAt;

	public Authority(AuthorityRole role, AppUser appUser, LocalDate assignedAt) {
		this.role = role;
		this.appUser = appUser;
		this.assignedAt = assignedAt;
	}

	public Authority(AuthorityRole role, AppUser appUser) {
		this.role = role;
		this.appUser = appUser;
	}

	public Authority(AppUser appUser) {
		this.appUser = appUser;
	}

	public Authority(AuthorityRole role) {
		this.role = role;
	}
}
