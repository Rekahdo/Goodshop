package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AddressDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AddressService {

    public ResponseEntity<?> addAddress(Long userId, @Valid AddressDto dto) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editAddress(Long userId, @Valid AddressDto dto) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAddresses(Long userId) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAddress(Long userId, Long addressId) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteAddress(Long userId, Long addressId) {

        return ResponseEntity.ok().build();
    }
}
