package com.rekahdo.goodshop.file_service.dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.rekahdo.goodshop.file_service.enums.Purpose;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@JsonFilter("fileDtoFilter")
public class FileDto extends EntityDto<FileDto> {

    private Long id;
    private String name;
    private String type;
    private String size;
    private Purpose purpose;
    private Long userId;
    private LocalDateTime updatedAt;
    private byte[] fileData;

    public FileDto() {
        super("fileDtoFilter", Set.of("id", "name", "purpose", "_links"));
    }

}