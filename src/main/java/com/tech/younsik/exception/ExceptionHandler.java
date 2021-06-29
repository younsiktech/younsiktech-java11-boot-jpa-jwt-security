package com.tech.younsik.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.younsik.constant.ErrorCode;
import com.tech.younsik.dto.response.ErrorResponse;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice(basePackages = {"com.tech.younsik.controller"})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    private final org.apache.commons.logging.Log log = LogFactory.getLog(getClass());

    private final ObjectMapper objectMapper = new ObjectMapper();

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ErrorResponse.Builder()
                .setCode(ErrorCode.BAD_REQUEST)
                .setMsg(e.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ErrorResponse.Builder()
            .setCode(ErrorCode.INVALID_ARGS)
            .setMsg(e.getMessage())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse invalidParameterException(InvalidParameterException e) {
        return new ErrorResponse.Builder()
            .setCode(ErrorCode.INVALID_PARAMS)
            .setMsg(e.getMessage())
            .setFieldErrorData(e.getErrorList())
            .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = HttpStatusCodeException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> httpStatusCodeException(HttpStatusCodeException e) {
        try {
            log.error(String.format("HttpStatusCodeException : %s", e.getMessage()));
            String body = e.getResponseBodyAsString();
            ErrorResponse errorResponse = objectMapper.readValue(body, ErrorResponse.class);
            return new ResponseEntity<>(errorResponse, e.getStatusCode());
        } catch (Exception ex) {
            return new ResponseEntity<>(new ErrorResponse.Builder()
                .setCode(e.getStatusCode().value() * 1000)
                .setMsg(e.getMessage())
                .build(), e.getStatusCode());
        }
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse userException(UserException e) {
        int code = ErrorCode.BAD_REQUEST;

        switch (e.getType()) {
            case INVALID_EMAIL:
                code = ErrorCode.INVALID_EMAIL;
                break;
            case INVALID_PASSWORD:
                code = ErrorCode.INVALID_PASSWORD;
                break;
            case INVALID_NAME:
                code = ErrorCode.INVALID_NAME;
                break;
            case ALREADY_EXIST:
                code = ErrorCode.ALREADY_EXIST;
                break;
            case USER_NOT_FOUND:
                code = ErrorCode.USER_NOT_FOUND;
                break;
            default:
                break;
        }

        return new ErrorResponse.Builder()
                .setCode(code)
                .setMsg(e.getMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorResponse authException(AuthException e) {
        int code = ErrorCode.UNAUTHOIRZED;

        switch (e.getType()) {
            case EMPTY_AUTH:
                code = ErrorCode.EMPTY_AUTH;
                break;
            case INVALID_AUTH:
                code = ErrorCode.INVALID_AUTH;
                break;
            case EXPIRED_AUTH:
                code = ErrorCode.EXPIRED_AUTH;
                break;
            default:
                break;
        }

        return new ErrorResponse.Builder()
                .setCode(code)
                .setMsg(e.getMessage())
                .build();
    }
}
