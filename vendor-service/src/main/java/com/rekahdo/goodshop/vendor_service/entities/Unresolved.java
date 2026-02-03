package com.rekahdo.goodshop.vendor_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "unresolved")
public class Unresolved implements ApiEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String reason;

	private boolean queried;

	@Column(nullable = false)
	private LocalDateTime generatedAt;

	@JoinColumn(nullable = false, name = "vendor_id")
	@ManyToOne
	@JsonBackReference
	private Vendor vendor;

	public Unresolved(UnresolvedReason reason, Vendor vendor) {
		this.reason = reason.index;
		this.vendor = vendor;
		setGeneratedAt(LocalDateTime.now());
	}

	public Unresolved(UnresolvedReason reason) {
		this.reason = reason.index;
	}
}
