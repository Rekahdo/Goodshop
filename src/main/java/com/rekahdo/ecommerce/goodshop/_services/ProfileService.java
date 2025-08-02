package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.entities.ProfileDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._entities.Profile;
import com.rekahdo.ecommerce.goodshop._mappers.ProfileMapper;
import com.rekahdo.ecommerce.goodshop._repository.ProfileRepository;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.ProfileMJV;
import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
public class ProfileService {

	@Autowired
	private ProfileRepository repository;

	@Autowired
	private ProfileMapper mapper;

	@Autowired
	private ProfileMJV mjv;

	private ApiLogger logger = new ApiLogger(getClass());

	public ResponseEntity<?> setProfile(Long userId, ProfileDto dto) {
		Profile profile = repository.findByAppUserId(userId).orElse(new Profile(new AppUser(userId)));
		mapper.updateEntity(dto, profile);
		repository.save(profile);
		logger.log(String.format("User with ID '%d' profile has been modified", userId));
		return ResponseEntity.ok().build();
	}

	public ResponseEntity<?> getProfile(Long userId) {
		Optional<Profile> optional = repository.findByAppUserId(userId);
		if(optional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User profile not found");

		ProfileDto dto = mapper.toDto(optional.get());
		return ResponseEntity.ok(mjv.selfFilter(dto));
	}

	public ResponseEntity<?> deleteProfile(Long userId) {

		return null;
	}
}
