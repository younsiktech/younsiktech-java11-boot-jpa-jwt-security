package com.tech.younsik.exception;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class UserException extends ApiException {

    private static final long serialVersionUID = 9201111170007829481L;

    public enum Type {
        BAD_REQUEST,
        INVALID_EMAIL,
        INVALID_NAME,
        INVALID_PASSWORD,
        ALREADY_EXIST,
        USER_NOT_FOUND,
        WRONG_PASSWORD
    }

    private Type type;

    private Map data;

    public UserException(Type type) {
        this.type = type;
    }

    public UserException(Type type, Map data) {
        this.type = type;
        this.data = data;
    }

    public UserException(String message, Type type) {
        super(message);
        this.type = type;
    }

    public UserException(String message, Type type, Map data) {
        super(message);
        this.type = type;
        this.data = data;
    }

    public UserException(String message, Throwable cause, Type type) {
        super(message, cause);
        this.type = type;
    }

    public UserException(Throwable cause, Type type) {
        super(cause);
        this.type = type;
    }

    public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Type type) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Map getData() {
        return data;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isEmpty(super.getMessage())) {
            return type.toString();
        } else {
            return super.getMessage();
        }
    }
}
