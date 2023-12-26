package com.example.core.common;

import com.example.core.common.exception.*;
import com.example.core.common.model.dto.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Advice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BaseException badRequest(){
        BaseException exception = new BaseException();
        exception.setStatus(400);
        exception.setError("Bad request");
        exception.setMessage("Bad request parameter");
        return exception;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorisedRequestException.class)
    public BaseException unauthorised(){
        BaseException exception = new BaseException();
        exception.setStatus(HttpStatus.UNAUTHORIZED.value());
        exception.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        exception.setMessage("Incorrect authentication info");
        return exception;
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenRequestException.class)
    public BaseException forbidden(){
        BaseException exception = new BaseException();
        exception.setStatus(HttpStatus.FORBIDDEN.value());
        exception.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
        exception.setMessage("Not allowed");
        return exception;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public BaseException notfound(){
        BaseException exception = new BaseException();
        exception.setStatus(HttpStatus.NOT_FOUND.value());
        exception.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        exception.setMessage("Not found path");
        return exception;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public BaseException internalServerError(){
        BaseException exception = new BaseException();
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        exception.setMessage("Internal server error");
        return exception;
    }
}
