package com.rekahdo.goodshop.file_service.validations.validators;

import com.rekahdo.goodshop.file_service.validations.annotations.DocType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class DocValidator implements ConstraintValidator<DocType, MultipartFile> {

    public final List<String> allowedTypes =
            List.of("image/jpeg", "image/jpg", "image/png", "application/pdf");

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()){
            String message = String.format("Field value not present. Accepted file types : [ %s ]",
                    String.join(", ", allowedTypes.stream().map(String::toUpperCase).toList()));

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        String contentType = file.getContentType();
        if(contentType == null || !allowedTypes.contains(contentType.toLowerCase())){
            String message = String.format("File type not present with file name '%s'. Accepted file types : [ %s ]",
                    file.getOriginalFilename(), String.join(", ", allowedTypes.stream().map(String::toUpperCase).toList()));

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        return true;
    }

}
