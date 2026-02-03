package com.rekahdo.goodshop.vendor_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "businesses")
public class Business implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String registrationName;

	private String tradingName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private boolean emailVerified;

	private boolean phoneAdded;
	private boolean addressAdded;
	private boolean certificateAdded;

	public Business(Long id) {
		this.id = id;
	}


}
