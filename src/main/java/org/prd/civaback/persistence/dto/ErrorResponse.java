package org.prd.civaback.persistence.dto;

import org.prd.civaback.util.EErrorType;

import java.util.Date;

public record ErrorResponse(
        int statusCode,
        Date timestamp,
        EErrorType error,
        String message
) { }