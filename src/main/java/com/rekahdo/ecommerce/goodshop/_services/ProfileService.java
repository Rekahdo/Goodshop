package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.ProfileDto;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfileService {

	public ResponseEntity<?> getProfile(Long userId) {

		return null;
	}

	public ResponseEntity<?> putProfile(Long userId, ProfileDto dto) {

		return null;
	}

	public ResponseEntity<?> patchProfile(Long userId, ProfileDto dto) {

		return null;
	}

	public ResponseEntity<?> deleteProfile(Long userId) {

		return null;
	}
}
