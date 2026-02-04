package com.rekahdo.goodshop.vendor_service.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import com.rekahdo.goodshop.vendor_service.utilities.IdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vendors")
public class Vendor implements ApiEntity{

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

	public Vendor(Long userId) {
		this.userId = userId;
		setUid(IdGenerator.generateId());
		setApprovalStatus(ApprovalStatus.PENDING.index);
		setRegisteredAt(LocalDateTime.now());
	}

	public boolean isPendingReview(){
		return Objects.equals(ApprovalStatus.PENDING.index, approvalStatus);
	}

	public boolean isBeenReviewed(){
		return Objects.equals(ApprovalStatus.ON_GOING.index, approvalStatus);
	}

	public boolean isDenied(){
		return Objects.equals(ApprovalStatus.DENIED.index, approvalStatus);
	}

	public boolean isApproved(){
		return Objects.equals(ApprovalStatus.APPROVED.index, approvalStatus);
	}

}
