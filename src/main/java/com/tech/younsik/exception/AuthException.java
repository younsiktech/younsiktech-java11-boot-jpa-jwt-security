package com.tech.younsik.exception;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class AuthException extends ApiException {

    private static final long serialVersionUID = 923651117046829481L;

    public enum Type {
        UNAUTHOIRZED,
        EMPTY_AUTH,
        INVALID_AUTH,
        EXPIRED_AUTH
    }

    private Type type;

    private Map data;

    public AuthException(Type type) {
        this.type = type;
    }

    public AuthException(Type type, Map data) {
        this.type = type;
        this.data = data;
    }

    public AuthException(String message, Type type) {
        super(message);
        this.type = type;
    }

    public AuthException(String message, Type type, Map data) {
        super(message);
        this.type = type;
        this.data = data;
    }

    public AuthException(String message, Throwable cause, Type type) {
        super(message, cause);
        this.type = type;
    }

    public AuthException(Throwable cause, Type type) {
        super(cause);
        this.type = type;
    }

    public AuthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Type type) {
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
