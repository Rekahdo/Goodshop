package com.rekahdo.goodshop.file_service.dtos.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ImageDto extends RepresentationModel<ImageDto> implements ApiDto {
    
    private String name;
    private String type;
    private int width;
    private int height;
    private long bytes;
    private String size;

}