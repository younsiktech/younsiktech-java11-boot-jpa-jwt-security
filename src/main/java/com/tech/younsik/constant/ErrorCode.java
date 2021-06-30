package com.tech.younsik.constant;

public interface ErrorCode {
    // REQUEST
    int BAD_REQUEST = 400000;

    // controller
    int INVALID_ARGS = 400001;
    int INVALID_PARAMS = 400002;

    // user (101~199)
    int ALREADY_EXIST  = 400101;
    int USER_NOT_FOUND = 400102;

    // user request (111~119)
    int INVALID_EMAIL = 400111;
    int INVALID_PASSWORD = 400112;
    int INVALID_NAME = 400113;
    
    // UNAUTHORIZED
    int UNAUTHOIRZED = 401000;
    // header에 auth 없음
    int EMPTY_AUTH = 401001;
    // 잘못된 access_token
    int INVALID_AUTH = 401002;
    // 만료된 access_token
    int EXPIRED_AUTH = 401003;
    // undefined token error
    int UNDEFINED_AUTH_ERROR = 401004;
    
    
    int INTERNAL_SERVER_ERROR = 500000;
    
    // db (500100~)
    int GENERATE_KEY_ERROR = 500101;

}
