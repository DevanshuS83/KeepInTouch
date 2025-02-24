package com.keepintouch.kit.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    private static final long MAX_FILE_SIZE = 2 * 1024 * 1024;  // 2MB file

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if(file==null || file.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File cannot be empty").addConstraintViolation();
            return false;
        }

        if(file.getSize() > MAX_FILE_SIZE){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("File size cannot be greater than " + MAX_FILE_SIZE).addConstraintViolation();
            return false;
        }

        return true;
    }
}
