package com.rekahdo.ecommerce.goodshop._repository;

import com.rekahdo.ecommerce.goodshop._entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByAppUserId(Long userId);
}