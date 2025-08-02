package com.rekahdo.ecommerce.goodshop._entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "forgot_passwords")
public class ForgotPassword {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer otp;

	@OneToOne
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	private LocalDateTime expireAt;

	public ForgotPassword(Integer otp, AppUser appUser, LocalDateTime expireAt) {
		this.otp = otp;
		this.appUser = appUser;
		this.expireAt = expireAt;
	}

	public boolean hasExpired(){
		return LocalDateTime.now().isAfter(expireAt);
	}
}
