package com.rekahdo.goodshop.vendor_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contact_persons")
public class Contact implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer title;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer role;

	private boolean phoneAdded;
	private boolean emergencyPhoneAdded;
	private boolean idProofAdded;

	public Contact(Long id) {
		this.id = id;
	}
}
