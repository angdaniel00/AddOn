package com.adds.addon.handlers;

import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AddsControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({SignatureException.class})
    @ResponseBody
    public ResponseEntity<?> getException(Exception e){
        
    }

}
