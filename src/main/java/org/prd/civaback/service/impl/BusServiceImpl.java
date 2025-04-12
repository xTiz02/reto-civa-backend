package org.prd.civaback.service.impl;

import org.prd.civaback.persistence.dto.PageResponse;
import org.prd.civaback.persistence.entity.BusEntity;
import org.prd.civaback.persistence.repository.BusRepository;
import org.prd.civaback.service.BusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    public BusServiceImpl(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse getAllBuses(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<BusEntity> busPage = busRepository.findAll(pageable);
        return new PageResponse(
                busPage.getContent(),
                busPage.getNumber(),
                busPage.getSize(),
                busPage.getTotalPages(),
                busPage.isLast(),
                busPage.isFirst()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public BusEntity getBusById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus no encontrado con id: " + id));
    }
}