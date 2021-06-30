package com.tech.younsik.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    
    String message() default "Invalid Password";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    public int min() default 8;
    
    public int max() default 255;
}
