package com.tech.younsik.exception;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class VacationException extends ApiException {

    private static final long serialVersionUID = 3451111170007829481L;
    public enum Type {
        VACATION_NOT_FOUND,
        INVALID_VACATION_PERIOD,
        VACATION_NOT_READY,
    }

    private Type type;

    private Map data;

    public VacationException(Type type) {
        this.type = type;
    }

    public VacationException(Type type, Map data) {
        this.type = type;
        this.data = data;
    }

    public VacationException(String message, Type type) {
        super(message);
        this.type = type;
    }

    public VacationException(String message, Type type, Map data) {
        super(message);
        this.type = type;
        this.data = data;
    }

    public VacationException(String message, Throwable cause, Type type) {
        super(message, cause);
        this.type = type;
    }

    public VacationException(Throwable cause, Type type) {
        super(cause);
        this.type = type;
    }

    public VacationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Type type) {
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
