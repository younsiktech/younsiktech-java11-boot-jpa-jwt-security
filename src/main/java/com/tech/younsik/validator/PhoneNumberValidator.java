package com.tech.younsik.validator;

import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private int max;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        // 숫자만 1개 이상 최대자리 regex
        String regex = "^(?=.*\\d)[\\d]{1," + max + "}$";
        return Pattern.compile(regex).matcher(phone).find();
    }
}
