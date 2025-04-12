package org.prd.civaback.config.exception;

import org.prd.civaback.util.EErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredTokenException extends RuntimeException{

    private EErrorType errorType;

    public ExpiredTokenException(String token, String message, EErrorType errorType) {
        super(String.format("Fallo porque [%s]: %s", token, message));
        this.errorType = errorType;
    }

    public EErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(EErrorType errorType) {
        this.errorType = errorType;
    }
}