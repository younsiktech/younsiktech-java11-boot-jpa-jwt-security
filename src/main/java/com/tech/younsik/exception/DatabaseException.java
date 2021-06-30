package com.tech.younsik.exception;


import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class DatabaseException extends ApiException {
    
    private static final long serialVersionUID = 9233425670829231L;
    
    public enum Type {
        GENERATE_KEY_ERROR,
        UNDEFINED_DATABASE_ERROR
    }
    
    private DatabaseException.Type type;
    
    private Map data;
    
    public DatabaseException(DatabaseException.Type type) {
        this.type = type;
    }
    
    public DatabaseException(DatabaseException.Type type, Map data) {
        this.type = type;
        this.data = data;
    }
    
    public DatabaseException(String message, DatabaseException.Type type) {
        super(message);
        this.type = type;
    }
    
    public DatabaseException(String message, DatabaseException.Type type, Map data) {
        super(message);
        this.type = type;
        this.data = data;
    }
    
    public DatabaseException(String message, Throwable cause, DatabaseException.Type type) {
        super(message, cause);
        this.type = type;
    }
    
    public DatabaseException(Throwable cause, DatabaseException.Type type) {
        super(cause);
        this.type = type;
    }
    
    public DatabaseException(String message, Throwable cause, boolean enableSuppression,
        boolean writableStackTrace, DatabaseException.Type type) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.type = type;
    }
    
    public DatabaseException.Type getType() {
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
