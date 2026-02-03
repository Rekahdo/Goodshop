package com.rekahdo.goodshop.phone_service.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "country_codes")
public class Code implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String number;

	public Code(Long id) {
		this.id = id;
	}

	public Code(String number) {
		this.number = number;
	}
}
