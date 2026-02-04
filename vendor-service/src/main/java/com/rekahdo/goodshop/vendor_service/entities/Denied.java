package com.rekahdo.goodshop.vendor_service.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "denied_vendors")
public class Denied implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uid;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private Integer approvalStatus;

	@OneToOne
	@JoinColumn(name = "business_id")
	private Business business;

	@OneToOne
	@JoinColumn(name = "contact_person_id")
	private Contact contact;

	@OneToOne
	@JoinColumn(name = "bank_id")
	private Bank bank;

	@OneToMany(mappedBy = "vendor")
	@JsonManagedReference
	private List<Unresolved> unresolved;

	@Column(nullable = false)
	private LocalDateTime registeredAt;

	public Denied(Long userId) {
		this.userId = userId;
	}

}
