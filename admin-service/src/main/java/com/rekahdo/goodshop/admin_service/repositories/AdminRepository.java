package com.rekahdo.goodshop.admin_service.repositories;

import com.rekahdo.goodshop.admin_service.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUserId(Long userId);

    @Modifying @Transactional
    @Query("UPDATE Admin SET role = ?2 WHERE userId = ?1")
    void updateUserRole(Long userId, Integer role);

    @Modifying @Transactional
    @Query("DELETE FROM Admin WHERE userId = ?1")
    void deleteByUserId(Long userId);

}