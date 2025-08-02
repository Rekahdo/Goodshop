package com.rekahdo.ecommerce.goodshop._repository;

import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByUsernameIgnoreCase(String username);

	Optional<AppUser> findByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE AppUser SET password = ?1 WHERE email = ?2")
	void updatePassword(String password, String email);

	boolean existsByUsername(String username);

	@Transactional @Modifying
	void deleteByUsername(String username);

}