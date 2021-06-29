package com.tech.younsik.validator;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {
    private int min;
    private int max;

    @Override
    public void initialize(Password constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // 영어 대소문자, 숫자, 특수문자 1개 이상 regex
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{" + min + "," + max + "}";
        return Pattern.compile(regex).matcher(password).find();
    }
}
