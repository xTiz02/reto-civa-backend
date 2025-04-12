package org.prd.civaback.persistence.dto;

import org.prd.civaback.persistence.entity.BusEntity;

import java.util.List;

public record PageResponse(
        List<BusEntity> content,
        int pageNumber,
        int pageSize,
        int totalPages,
        boolean lastPage,
        boolean firstPage
) {
}