package com.rekahdo.goodshop.vendor_service.repositories;

import com.rekahdo.goodshop.vendor_service.entities.Unresolved;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UnresolvedRepository extends JpaRepository<Unresolved, Long> {

    @Modifying @Transactional
    @Query("UPDATE Unresolved SET queried = true WHERE reason IN ?2 AND vendor.id = (SELECT v.id FROM Vendor v WHERE v.userId = ?1)")
    void query(Long userId, List<String> reasons);

    @Query("SELECT u FROM Unresolved u WHERE vendor.id = (SELECT v.id FROM Vendor v WHERE v.userId = ?1)")
    List<Unresolved> findByUserId(Long userId);

    @Query("SELECT u FROM Unresolved u WHERE vendor.id = (SELECT v.id FROM Vendor v WHERE v.userId = ?1) AND reason = ?2")
    Optional<Unresolved> findByUserIdAndReason(Long userId, String reason);

    @Modifying @Transactional
    @Query("DELETE FROM Unresolved WHERE vendor.id = (SELECT v.id FROM Vendor v WHERE v.userId = ?1)")
    void deleteByUserId(Long userId);

    @Modifying @Transactional
    @Query("DELETE FROM Unresolved WHERE vendor.id = (SELECT v.id FROM Vendor v WHERE v.userId = ?1) AND reason = ?2")
    void deleteByUserIdAndReason(Long userId, String reason);

}