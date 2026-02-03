package com.rekahdo.goodshop.file_service.repositories;

import com.rekahdo.goodshop.file_service.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByUserId(Long userId);

    Optional<File> findByUserIdAndName(Long userId, String name);

    Optional<File> findByUserIdAndPurpose(Long userId, Integer purpose);

    @Query("SELECT f FROM File f WHERE f.userId = ?1 AND f.purpose = ?2")
    List<File> findAllByUserIdAndPurpose(Long userId, Integer purpose);

    List<File> findByUserIdAndPurposeAndNameIn(Long userId, Integer purpose, List<String> names);

    Optional<File> findByName(String name);

    @Query("SELECT f FROM File f WHERE f.userId = :userId AND f.purpose = :purpose AND f.name IN :names")
    List<File> findFilesByAttributes(@Param("userId") Long userId, @Param("purpose") Integer purpose,
                                     @Param("names") List<String> names);

    @Modifying @Transactional
    void deleteByUserIdAndPurpose(Long userId, Integer purpose);

    @Modifying @Transactional
    @Query("DELETE FROM File f WHERE f.userId = :userId AND f.purpose = :purpose AND f.name IN :names")
    void deleteFilesByAttributes(@Param("userId") Long userId, @Param("purpose") Integer purpose,
                                 @Param("names") List<String> names);

}