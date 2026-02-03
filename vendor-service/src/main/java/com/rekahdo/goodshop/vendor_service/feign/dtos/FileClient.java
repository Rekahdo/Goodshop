package com.rekahdo.goodshop.vendor_service.feign.dtos;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.LocalDateTime;

@Getter
@Setter
public class FileClient {

    private Long id;
    private String name;
    private String type;
    private String size;
    private String purpose;
    private Long userId;
    private URI self;
    private LocalDateTime updatedAt;

}