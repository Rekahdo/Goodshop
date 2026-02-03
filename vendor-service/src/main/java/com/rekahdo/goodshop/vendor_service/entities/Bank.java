package com.rekahdo.goodshop.vendor_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "banks")
public class Bank implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String accountName;

	@Column(nullable = false)
	private String bankName;

	@Column(nullable = false)
	private String accountNumber;

	@Column(nullable = false)
	private Integer accountType;

	private boolean addressAdded;

	public Bank(Long id) {
		this.id = id;
	}
}
