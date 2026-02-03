package com.rekahdo.goodshop.vendor_service.dtos.entities;

import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UnresolvedDto implements ApiDto {

	private Long id;
	private UnresolvedReason reason;
	private String message;
	private boolean queried;
	private LocalDateTime generatedAt;

}