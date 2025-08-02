package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AuthorityDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._entities.Authority;
import com.rekahdo.ecommerce.goodshop._mappers.AuthorityMapper;
import com.rekahdo.ecommerce.goodshop._repository.AuthorityRepository;
import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.AuthorityNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.NotAcceptableException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.AuthorityMJV;
import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import com.rekahdo.ecommerce.goodshop.utilities.AuthUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityRepository repository;

	@Autowired
	private AuthorityMapper mapper;

	@Autowired
	private AuthorityMJV mjv;

	@Value("${adminKey}")
	private String adminKey;

	private final ApiLogger logger = new ApiLogger(getClass());

	public ResponseEntity<?> assignAuthority(Long userId, AuthorityDto dto) {
		if(AuthUser.IS_NOT_AN_ADMIN() && dto.adminKeyIsNotValid(adminKey))
			throw new AccessDeniedException("Please provide the right admin key to modify user roles.");

		if(dto.getRole().equals(AuthorityRole.USER))
			throw new NotAcceptableException(dto.getRole().getValue());

		Authority authority = repository.findByAppUserId(userId).orElse(new Authority(new AppUser(userId)));
		mapper.updateEntity(dto, authority);

		if(authority.getAssignedAt() == null || dto.getRole() != authority.getRole())
			authority.setAssignedAt(LocalDate.now());

		repository.save(authority);
		logger.log(String.format("User with ID '%d' has been given the '%s' role", userId, dto.getRole()));
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<?> getAuthority(Long userId) {
		Optional<Authority> optional = repository.findByAppUserId(userId);
		if(optional.isEmpty()) throw new AuthorityNotFoundException();

		AuthorityDto dto = mapper.toDto(optional.get());
		return ResponseEntity.ok(mjv.selfFilter(dto));
	}

	public ResponseEntity<?> deleteAuthority(Long userId) {
		repository.deleteByAppUserId(userId);
		logger.log(String.format("User ID '%d' role has been successfully deleted", userId));
		return ResponseEntity.ok().build();
	}
}