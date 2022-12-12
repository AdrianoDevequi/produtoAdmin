package com.admin.spring.customAnnotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.admin.spring.web.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto userDto = (UserDto) obj;
        return userDto.getPassword().equals(userDto.getMatchingPassword());

    }

}