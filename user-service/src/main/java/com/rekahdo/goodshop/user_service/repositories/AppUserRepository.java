package com.rekahdo.goodshop.user_service.repositories;

import com.rekahdo.goodshop.user_service.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT u FROM AppUser u WHERE LOWER(uid) = LOWER(?1) OR LOWER(email) = LOWER(?1)")
    Optional<AppUser> findByUidOrEmail(String uidEmail);

    @Query("SELECT u FROM AppUser u WHERE LOWER(username) = LOWER(?1) OR LOWER(uid) = LOWER(?1) OR LOWER(email) = LOWER(?1)")
    Optional<AppUser> findByUsernameOrUidOrEmail(String username);

    @Query("SELECT u FROM AppUser u WHERE LOWER(username) = LOWER(?1) OR LOWER(email) = LOWER(?2)")
    Optional<AppUser> findByUsernameOrEmail(String username, String email);

    Optional<AppUser> findByUid(String uid);

    Optional<AppUser> findByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);

    List<AppUser> findByUsernameIgnoreCase(String username);

    Optional<AppUser> findByEmailIgnoreCase(String email);

    boolean existsByUsernameIgnoreCaseOrEmailIgnoreCase(String username, String email);

    Optional<AppUser> findByEmail(String value);

    @Modifying @Transactional
    @Query("UPDATE AppUser SET verified = true WHERE email = ?1")
    void verifyAccount(String email);

    @Modifying @Transactional
    @Query("UPDATE AppUser SET password = ?1 WHERE email = ?2")
    void updatePassword(String password, String email);

    @Modifying @Transactional
    @Query("DELETE FROM AppUser u WHERE LOWER(uid) = LOWER(?1) OR LOWER(email) = LOWER(?1)")
    int deleteByUidOrEmail(String uidEmail);

}