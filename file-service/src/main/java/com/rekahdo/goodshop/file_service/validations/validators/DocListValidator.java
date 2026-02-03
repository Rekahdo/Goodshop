package com.rekahdo.goodshop.file_service.validations.validators;

import com.rekahdo.goodshop.file_service.validations.annotations.DocListType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocListValidator implements ConstraintValidator<DocListType, List<MultipartFile>> {

    private final DocValidator docValidator;

    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext context) {
        return files.stream().allMatch(file -> docValidator.isValid(file, context));
    }

}
