package com.rekahdo.goodshop.user_service.entities;

import com.rekahdo.goodshop.user_service.utilities.UserIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class AppUser implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uid;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	private boolean verified;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	public AppUser(Long id) {
		this.id = id;
	}

	public AppUser(String username, String password, String email, boolean verified) {
		this.uid = UserIdGenerator.generateId();
		this.username = username;
		this.password = password;
		this.email = email;
		this.verified = verified;
		this.createdAt = LocalDateTime.now();
	}

	public boolean isNotVerified(){
		return !isVerified();
	}

}
