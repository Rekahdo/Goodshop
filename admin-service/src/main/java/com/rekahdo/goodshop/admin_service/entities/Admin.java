package com.rekahdo.goodshop.admin_service.entities;

import com.rekahdo.goodshop.admin_service.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admins")
public class Admin implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;
	private Integer role;
	private LocalDate assignedAt;

	public Admin(Long userId) {
		this.userId = userId;
	}

	public Admin(Long userId, Role role) {
		this.userId = userId;
		this.role = role.index;
		this.assignedAt = LocalDate.now();
	}

	public Admin(Long userId, String role) {
		this(userId, Role.valueOf(role));
	}

}
