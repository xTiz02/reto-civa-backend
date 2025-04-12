package org.prd.civaback.service;

import org.prd.civaback.persistence.dto.PageResponse;
import org.prd.civaback.persistence.entity.BusEntity;
import org.springframework.data.domain.Page;

public interface BusService {

    PageResponse getAllBuses(int page, int size);
    BusEntity getBusById(Long id);

}