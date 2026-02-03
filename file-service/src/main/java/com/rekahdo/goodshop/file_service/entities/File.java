package com.rekahdo.goodshop.file_service.entities;

import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.utilities.FileUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class File implements ApiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "user_id")
    private Long userId;

    private String name;
    private String type;

    @Column(name = "file_size")
    private String size;

    private Integer purpose;

    @Lob // annotation tells JPA that this field should be stored as a Large Object (BLOB) in the database.
    @Column(length = 10000000) // length attribute for large data
    private byte[] fileData;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public File(Long userId, Purpose purpose) {
        this.userId = userId;
        this.purpose = purpose.index;
    }

    public File(Long userId, Purpose purpose, MultipartFile multipartFile) {
        this(userId, purpose);
        setName(multipartFile.getOriginalFilename());
        setType(multipartFile.getContentType());
        setSize(FileUtils.getReadableFileSize(multipartFile.getSize()));
        setUpdatedAt(LocalDateTime.now());
        try {
            setFileData(FileUtils.compressImage(multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File update(MultipartFile multipartFile) {
        setName(multipartFile.getOriginalFilename());
        setType(multipartFile.getContentType());
        setSize(FileUtils.getReadableFileSize(multipartFile.getSize()));
        setUpdatedAt(LocalDateTime.now());
        try {
            setFileData(FileUtils.compressImage(multipartFile.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
