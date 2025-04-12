package org.prd.civaback.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.prd.civaback.config.exception.ExpiredTokenException;
import org.prd.civaback.persistence.dto.ErrorResponse;
import org.prd.civaback.util.EErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ErrorResponse> handlerGeneralExceptions(
            ExpiredTokenException exception,
            HttpServletRequest request,
            WebRequest webRequest)
    {
        System.out.println("ExpiredTokenException: " + exception.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                exception.getErrorType(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

    }

}