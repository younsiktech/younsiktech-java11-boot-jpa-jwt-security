package com.tech.younsik.exception;

import java.util.List;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class InvalidParameterException extends ApiException {

    private List<ObjectError> objectErrorList;

    public InvalidParameterException(BindingResult result) {
        super("Invalid Parameter Error");
        this.objectErrorList = result.getAllErrors();
    }

    public List<ObjectError> getErrorList() {
        return objectErrorList;
    }
}