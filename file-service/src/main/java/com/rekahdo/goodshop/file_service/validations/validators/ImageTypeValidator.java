package com.rekahdo.goodshop.file_service.validations.validators;

import com.rekahdo.goodshop.file_service.validations.annotations.ImageType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageTypeValidator implements ConstraintValidator<ImageType, MultipartFile> {

    private List<String> allowedTypes;

    @Override
    public void initialize(ImageType constraintAnnotation) {
        this.allowedTypes = List.of(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) return false;

        String contentType = file.getContentType();
        return contentType != null && allowedTypes.contains(contentType.toLowerCase());
    }

}
