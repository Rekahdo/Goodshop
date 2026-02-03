package com.rekahdo.goodshop.phone_service.entities;

import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "phones")
public class Phone implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String number;

	private Integer purpose;
	private boolean verified;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@OneToOne
	@JoinColumn(name = "country_code_id")
	private Code code;

	public Phone(Long userId, PhonePurpose purpose) {
		this.userId = userId;
		this.purpose = purpose.index;
	}

	public String phone(){
		return String.format("%s%s", code.getNumber(), number);
	}

}
