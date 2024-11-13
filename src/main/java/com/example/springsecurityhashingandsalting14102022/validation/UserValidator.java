package com.example.springsecurityhashingandsalting14102022.validation;

import com.example.springsecurityhashingandsalting14102022.ds.RegisterUser;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterUser.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterUser user = (RegisterUser) target;
        if(!user.getPassword().equals(user.getRepeatedPassword())) {
            errors.rejectValue("password", null,
                    "Password and RepeatedPassword must be the same.");
        }
    }
}
