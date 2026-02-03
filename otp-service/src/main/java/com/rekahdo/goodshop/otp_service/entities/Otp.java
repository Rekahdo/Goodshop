package com.rekahdo.goodshop.otp_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "otps")
@NoArgsConstructor
public class Otp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer otp;

	@Column(nullable = false)
	private Integer purpose;

	private Long userId;

	@Column(nullable = false)
	private String sentTo;

	@Column(nullable = false)
	private boolean sentToEmail;

	@Column(nullable = false)
	private boolean verified;

	@Column(nullable = false)
	private LocalDateTime expireAt;

	public Otp(Integer otp, Long userId, String sentTo) {
		this.otp = otp;
		this.userId = userId;
		this.sentTo = sentTo;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expireAt);
	}
}
